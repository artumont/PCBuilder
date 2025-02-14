import type { Metadata } from "next";
import { Poppins } from "next/font/google";
import { ThemeProvider } from 'next-themes';
import ThemeToggle from '@/components/theme';
import "./globals.css";

const poppins = Poppins({
    variable: "--font-poppins",
    subsets: ["latin"],
    weight: ["100", "200", "300", "400", "500", "600", "700", "800", "900"],
});

export const metadata: Metadata = {
    title: "PCBuilder - Build and Customize Your PC",
    description: "PCBuilder is a comprehensive web application for building and customizing PC configurations with real-time compatibility checking and price comparisons.",
    keywords: "PCBuilder, PC configuration, PC parts, compatibility checking, price comparison, build PC, customize PC",
    authors: [
        { name: "Artu (@artumont)" },
        { name: "Luis (@prodanyboy)" },
        { name: "Gerardo (@SONRIXMX)" },
        { name: "Emmanuel (@Ultimateknight143)" },
        { name: "Jesus (@Jesus-Mendoza21)" },
        { name: "Guajardo (@IngGuajardo)" },
        { name: "Gilberto (@GilPeCa)" }
    ],
    applicationName: "PCBuilder",
    openGraph: {
        type: "website",
        title: "PCBuilder - Build and Customize Your PC",
        description: "A comprehensive web application for building and customizing PC configurations.",
        url: "https://github.com/artumont/PCBuilder",
    },
    icons: {
        icon: [
            { url: "/assets/logo-light.ico" },
            { url: "/assets/logo-dark.ico", media: "(prefers-color-scheme: dark)" },
        ],
    },
};

export default function RootLayout({
    children,
}: Readonly<{
    children: React.ReactNode;
}>) {
    return (
        <html lang="en" suppressHydrationWarning>
            <body
                className={`${poppins.variable} antialiased`}
            >
                <ThemeProvider 
                    attribute="class"
                    defaultTheme="system"
                    enableSystem={true}
                    storageKey="theme"
                    enableColorScheme
                    disableTransitionOnChange
                >
                    {children}
                    <ThemeToggle />
                </ThemeProvider>
            </body>
        </html>
    );
}
