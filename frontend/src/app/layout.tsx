import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
    title: "PCBuilder - Build and Customize Your PC",
    description: "PCBuilder is a comprehensive web application for building and customizing PC configurations with real-time compatibility checking and price comparisons.",
    keywords: "PCBuilder, PC configuration, PC parts, compatibility checking, price comparison, build PC, customize PC",
    authors: [{ name: "Artu" }],
    openGraph: {
        title: "PCBuilder - Build and Customize Your PC",
        description: "A comprehensive web application for building and customizing PC configurations with real-time compatibility checking and price comparisons.",
        url: "https://github.com/artumont/PCBuilder",
        type: "website",
        images: [
            {
                url: "/assets/og-image.png",
                width: 800,
                height: 600,
                alt: "PCBuilder Logo",
            },
        ],
    },
    twitter: {
        card: "summary_large_image",
        site: "@PCBuilder",
        creator: "@artumont",
        title: "PCBuilder - Build and Customize Your PC",
        description: "A comprehensive web application for building and customizing PC configurations with real-time compatibility checking and price comparisons.",
        images: ["/assets/og-image.png"],
    },
};

export default function RootLayout({
    children,
}: Readonly<{
    children: React.ReactNode;
}>) {
    return (
        <html lang="en">
            <body
                className={`antialiased`}
            >
                {children}
            </body>
        </html>
    );
}
