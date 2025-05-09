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
          { text: '抽象类', link: '/抽象类' },
          { text: '接口', link: '/接口' },
        ],
      },
      {
        text: '集合',
        base: '/6-集合/',
        link: '/index.md',
        collapsed: true,
        items: [
          { text: '集合框架', link: '/1-集合框架' },
          { text: '迭代器', link: '/2-迭代器' },
          { text: '泛型', link: '/3-泛型' },
          { text: '几种集合框架', link: '/4-几种集合框架' },
        ],
      },
      {
        text: '异常处理',
        base: '/7-异常处理/',
        link: '/index.md',
        collapsed: true,
        items: [
        ],
      },
      {
        text: '多线程',
        base: '/8-多线程/',
        link: '/index.md',
        collapsed: true,
        items: [
        ],
      },
      {
        text: 'IO与文件操作',
        base: '/9-IO与文件操作/',
        link: '/index.md',
        collapsed: true,
        items: [
        ],
      },
      {
        text: '往年题',
        base: '/往年题/',
        link: '/index.md',
        collapsed: true,
        items: [
          { text: '2018', link: '/2018.md' },
          { text: '2020', link: '/2020.md' },
          { text: '练习题', link: '/练习题.md' },
          { text: '模拟题一', link: '/模拟题一.md' },
          { text: '模拟题二', link: '/模拟题二.md' },
          { text: '模拟题三', link: '/模拟题三.md' },
        ],
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
