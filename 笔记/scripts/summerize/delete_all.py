import re
import os

def delete_all(text):
    # 删除所有以“:::”开头和结尾的文本块 (可分行)
    pattern = r':::.*?:::'
    return re.sub(pattern, '', text, flags=re.DOTALL)

if __name__ == "__main__":
    script_dir = os.path.dirname(os.path.abspath(__file__))
    docs_dir = os.path.join(script_dir, "../../docs")
    for root, dirs, files in os.walk(docs_dir):
        for file in files:
            if file.endswith(".md"):
                file_path = os.path.join(root, file)
                with open(file_path, "r", encoding="utf-8") as f:
                    text = f.read()
                    text = delete_all(text)
                    with open(file_path, "w", encoding="utf-8") as f:
                        f.write(text)
