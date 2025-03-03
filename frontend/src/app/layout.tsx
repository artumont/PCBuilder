import type { Metadata } from "next";
import { Poppins } from "next/font/google";
import { ThemeProvider } from 'next-themes';
import ThemeToggle from '@/components/ThemeToggle';
import { NavigationProvider } from '@/context/NavContext';
import { AuthProvider } from '@/context/AuthContext';
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
        { name: "Gilberto (@GilPeCa)" },
        { name: "Alan (@Alanhhdz)" },
    ],
    applicationName: "PCBuilder",
    openGraph: {
        type: "website",
        locale: "en_US",
        title: "PCBuilder - Build and Customize Your PC",
        description: "A comprehensive web application for building and customizing PC configurations.",
        siteName: "PCBuilder",
        url: "https://github.com/artumont/PCBuilder",
        images: [{ 
            url: "/assets/og-image.png",
            width: 1200,
            height: 630,
            alt: "PCBuilder - Build and Customize Your PC",
        }],
    },
    twitter: {
        card: "summary_large_image",
        title: "PCBuilder - Build and Customize Your PC",
        description: "A comprehensive web application for building and customizing PC configurations.",
        images: ["/assets/og-image.png"],
    },
    icons: {
        icon: [
            {
                media: '(prefers-color-scheme: light)',
                url: '/assets/logo-light.ico',
                href: '/assets/logo-light.ico',
            },
            {
                media: '(prefers-color-scheme: dark)',
                url: '/assets/logo-dark.ico',
                href: '/assets/logo-dark.ico',
            },
        ],
    },
    category: "technology",
};

export default function RootLayout({
    children,
}: Readonly<{
    children: React.ReactNode;
}>) {
    return (
        <html lang="en" suppressHydrationWarning>
            <body
                className={`${poppins.variable} antialiased min-h-screen`}
            >
                <AuthProvider>
                    <NavigationProvider>
                        <ThemeProvider 
                            attribute="class"
                            defaultTheme="system"
                            enableSystem={true}
                            storageKey="theme"
                        >
                            {children}
                            <ThemeToggle />
                        </ThemeProvider>
                    </NavigationProvider>
                </AuthProvider>
            </body>
        </html>
    );
}
