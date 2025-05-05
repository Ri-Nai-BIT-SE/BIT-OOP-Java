import os
import re

def find_first_heading(content):
    """
    查找正文中第一个不在:::details块内的一级标题
    """
    # 将内容分成行处理
    lines = content.split('\n')
    
    print("正在查找一级标题...")
    in_details_block = False
    for line in lines:
        # 检查是否进入或离开details块
        if line.strip().startswith(':::details'):
            in_details_block = True
            print(f"进入details块: {line}")
        elif line.strip() == ':::' and in_details_block:
            in_details_block = False
            print("离开details块")
        # 如果不在details块内且找到一级标题
        elif not in_details_block and line.strip().startswith('# '):
            title = line.strip()[2:].strip()
            print(f"找到一级标题: {title}")
            # 返回标题文本(去掉#和空格)
            return title
    
    # 如果没有找到一级标题
    print("未找到一级标题")
    return None

def process_file(file_path):
    """处理单个Markdown文件"""
    print(f"\n开始处理文件: {file_path}")
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            text = f.read()
        
        # 检查是否已经有title
        has_title = False
        if text.startswith('---'):
            parts = text.split('---', 2)
            if len(parts) >= 3:
                frontmatter = parts[1]
                content = parts[2]
                
                # 检查frontmatter中是否已有title
                if re.search(r'title:\s*[^\s]', frontmatter):
                    has_title = True
                    print(f"文件已有title，跳过: {file_path}")
        
        if has_title:
            return
        
        # 查找第一个一级标题
        title = None
        if text.startswith('---'):
            print("文件有frontmatter")
            parts = text.split('---', 2)
            if len(parts) >= 3:
                frontmatter = parts[1]
                content = parts[2]
                
                print(f"frontmatter内容: \n{frontmatter}")
                print(f"开始分析content内容...")
                
                title = find_first_heading(content)
                
                if title:
                    # 在frontmatter中添加title
                    if frontmatter.strip():
                        # 如果frontmatter不为空，确保末尾有换行
                        if not frontmatter.endswith('\n'):
                            frontmatter += '\n'
                        new_frontmatter = frontmatter + f'title: {title}\n'
                    else:
                        new_frontmatter = f'title: {title}\n'
                    
                    # 组合新文件内容
                    new_text = f"---{new_frontmatter}---{content}"
                    
                    # 保存修改后的文件
                    with open(file_path, 'w', encoding='utf-8') as f:
                        f.write(new_text)
                    print(f"已添加title '{title}': {file_path}")
                else:
                    print(f"未找到一级标题，跳过: {file_path}")
            else:
                print(f"frontmatter格式不正确，跳过: {file_path}")
        else:
            print("文件没有frontmatter")
            # 没有frontmatter的情况
            title = find_first_heading(text)
            if title:
                # 创建新的frontmatter并添加到文件开头
                new_text = f"---\ntitle: {title}\n---\n\n{text}"
                with open(file_path, 'w', encoding='utf-8') as f:
                    f.write(new_text)
                print(f"已添加frontmatter和title '{title}': {file_path}")
            else:
                print(f"未找到一级标题，跳过: {file_path}")
    
    except Exception as e:
        print(f"处理文件 {file_path} 时出错: {str(e)}")

if __name__ == "__main__":
    # 获取当前脚本所在目录
    script_dir = os.path.dirname(os.path.abspath(__file__))
    # 指定docs目录路径(以当前脚本为相对路径)
    docs_dir = os.path.join(script_dir, "../docs")
    
    print(f"准备处理目录: {docs_dir}")
    
    # 确认目录存在
    if not os.path.exists(docs_dir):
        print(f"目录不存在: {docs_dir}")
        exit(1)
    
    # 遍历docs目录下的所有md文件(包括子目录)
    file_count = 0
    for root, dirs, files in os.walk(docs_dir):
        for file in files:
            if file.endswith(".md"):
                file_path = os.path.join(root, file)
                process_file(file_path)
                file_count += 1
    
    print(f"\n处理完成，共处理了 {file_count} 个Markdown文件") 
