import type { Config } from "tailwindcss";

export default {
  content: [
    "./src/pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/components/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  darkMode: 'class',
  theme: {
    extend: {
      colors: {
        dark: {
          DEFAULT: '#090909',
          secondary: '#222222',
          terciary: '#1a1a1a'
        },
        light: {
          DEFAULT: '#f9f9f9',
          secondary: '#c4c4c4',
          terciary: '#ababab'
        }
      },
    },
  },
  plugins: [],
} satisfies Config;
