import requests
import json
from prompt import system_prompt
import os



if __name__ == "__main__":
    config = {}
    with open("config.json", "r") as f:
        config = json.load(f)
    script_dir = os.path.dirname(os.path.abspath(__file__))
    # 指定docs目录路径(以当前脚本为相对路径)
    docs_dir = os.path.join(script_dir, "../../docs")
    server = config["server"]
    api_key = config["api_key"]
    endpoint = config["endpoint"]
    model = config["model"]
    headers = {
        "Authorization": f"Bearer {api_key}",
        "Content-Type": "application/json",
    }
    def summarize(text: str) -> str:
        payload = {
            "model": model,
            "messages": [
                {"role": "system", "content": system_prompt},
                {"role": "user", "content": text},
            ],
            "stream": True,
        }
        
        response = requests.post(server + '/' + endpoint, headers=headers, json=payload, stream=True)
        result = ""
        # 去掉 <think> ... </think>
        think_content = ""
        is_thinking = True
        for line in response.iter_lines():
            if line:
                line = line.decode("utf-8")
                if line.startswith("data:"):
                    try:
                        data = json.loads(line[5:])
                        if data["choices"][0]["finish_reason"] is not None:
                            break
                        content = data["choices"][0]["delta"].get("content", "")
                        if content.startswith("<think>"):
                            think_content = content
                            is_thinking = True
                        elif content.startswith("</think>"):
                            is_thinking = False
                        elif is_thinking:
                            think_content += content
                        else:
                            result += content
                        print(content, end="", flush=True)
                    except:
                        pass

        return result.replace("```markdown", "").replace("```", "")

    # ../docs 里的所有 md 文件(包括子目录)
    for root, dirs, files in os.walk(docs_dir):
        for file in files:
            if file.endswith(".md"):
                file_path = os.path.join(root, file)
                with open(file_path, "r", encoding="utf-8") as f:
                    text = f.read()
                
                # 检查文件是否已经包含 AI 总结
                if ":::details AI总结" in text:
                    print(f"跳过已处理文件: {file_path}")
                    continue
                
                # 处理 frontmatter
                if text.startswith("---"):
                    parts = text.split("---", 2)
                    if len(parts) >= 3:
                        frontmatter = parts[1]
                        content = parts[2]
                        if len(content) < 40:
                            print(f"文件内容过短，跳过: {file_path}")
                            continue
                        
                        # 打印文件路径
                        print(f"正在处理: {file_path}")
                        
                        # 只对内容部分进行总结
                        summary = summarize(content)
                        
                        # 组合新文件内容：frontmatter + AI总结 + 原内容
                        new_text = f"---{frontmatter}---\n\n:::details AI总结\n{summary}\n:::\n{content}"
                    else:
                        # 处理格式不正确的情况
                        print(f"文件格式不正确，跳过: {file_path}")
                        continue
                else:
                    if len(text) < 40:
                        print(f"文件内容过短，跳过: {file_path}")
                        continue
                    
                    # 打印文件路径
                    print(f"正在处理: {file_path}")
                    
                    # 没有 frontmatter 的情况
                    content = text
                    summary = summarize(content)
                    new_text = f":::details AI总结\n{summary}\n:::\n{content}"

                # 确保目标目录存在
                # os.makedirs("../summary", exist_ok=True)
                
                # 保存到 ../summary 目录
                # output_path = f"../summary/{file}"
                # with open(output_path, "w", encoding="utf-8") as f:
                #     f.write(new_text)
                # print(f"已处理并保存: {output_path}")
                # 保存到原路径
                with open(file_path, "w", encoding="utf-8") as f:
                    f.write(new_text)
                print(f"已处理并保存: {file_path}")

