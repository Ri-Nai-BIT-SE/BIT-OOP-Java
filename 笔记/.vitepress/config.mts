import { defineConfig } from 'vitepress'
import { MermaidMarkdown, MermaidPlugin } from 'vitepress-plugin-mermaid';

// https://vitepress.dev/reference/site-config
export default defineConfig({
  title: "OOP-Java-Notes",
  description: "A VitePress Site",
  srcDir: './docs',
  base: '/BIT-OOP-Java/',
  themeConfig: {
    // https://vitepress.dev/reference/default-theme-config
    editLink: {
      pattern: 'https://github.com/Ri-Nai-BIT-SE/BIT-OOP-Java/edit/main/docs/:path',
      text: 'Edit this page on GitHub'
    },
    nav: [
      { text: 'Home', link: '/' },
    ],
    outline: [1, 5],
    sidebar: [
      {
        text: 'Java语言基础',
        base: '/1-Java语言基础/',
        link: '/index.md',
        collapsed: true,
        items: [
          { text: '标识符', link: '/1-标识符' },
          { text: '数据类型与变量常量', link: '/2-数据类型与变量常量' },
        ],
      },
      {
        text: '数组',
        base: '/2-数组/',
        link: '/index.md',
        collapsed: true,
        items: [
          { text: '数组', link: '/index.md' },
          { text: 'JVM', link: '/jvm.md' },
        ],
      },
      {
        text: '类和对象',
        base: '/3-类和对象/',
        link: '/index.md',
        collapsed: true,
        items: [
        ],
      },
      {
        text: '继承',
        base: '/4-继承/',
        link: '/index.md',
        collapsed: true,
        items: [
        ],
      },
      {
        text: '多态',
        base: '/5-多态/',
        link: '/index.md',
        collapsed: true,
        items: [
        ],
      },
      {
        text: '集合',
        base: '/6-集合/',
        link: '/index.md',
        collapsed: true,
      },
    ],
    socialLinks: [
      { icon: 'github', link: 'https://github.com/Ri-Nai-BIT-SE/BIT-OOP-Java' }
    ],
  },
  markdown: {
    math: true,
    config(md) {
      md.use(MermaidMarkdown);
    },
  },
  vite: {
    plugins: [MermaidPlugin()],
    optimizeDeps: {
      include: ['mermaid'],
    },
    ssr: {
      noExternal: ['mermaid'],
    },
  },
})
