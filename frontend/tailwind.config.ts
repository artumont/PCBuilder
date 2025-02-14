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
          secondary: '#222222'
        },
        light: {
          DEFAULT: '#c0c0c0',
          secondary: '#929292'
        }
      },
    },
  },
  plugins: [],
} satisfies Config;
