'use client'

export default function PrebuiltGuide() {
    return (
        <div className="space-y-8">
            <h2 className="text-2xl font-bold">Recommended Prebuilt PCs</h2>

            <div className="p-4 rounded-lg mb-8">
                <h3 className="text-lg font-semibold mb-3">Why Consider a Prebuilt PC?</h3>
                <ul className="list-disc pl-6 space-y-2">
                    <li>Professional assembly and testing</li>
                    <li>Single warranty coverage for all components</li>
                    <li>Ready to use out of the box</li>
                    <li>Technical support included</li>
                    <li>Sometimes cheaper during sales than DIY</li>
                </ul>
            </div>

            <section className="space-y-8">
                <div className="border p-6 rounded-lg space-y-6">
                    <h3 className="text-xl font-semibold">Entry-Level Gaming ($800-1000)</h3>
                    <div className="space-y-4">
                        <div>
                            <p className="font-medium">Specifications:</p>
                            <ul className="list-disc pl-6 space-y-1">
                                <li>CPU: Intel Core i5-12400F</li>
                                <li>GPU: NVIDIA RTX 3060</li>
                                <li>RAM: 16GB DDR4-3200</li>
                                <li>Storage: 500GB NVMe SSD + 1TB HDD</li>
                                <li>PSU: 650W 80+ Bronze</li>
                                <li>Cooling: Stock CPU cooler + 3 case fans</li>
                            </ul>
                        </div>
                        <div>
                            <p className="font-medium">Gaming Performance (1080p):</p>
                            <ul className="list-disc pl-6 space-y-1">
                                <li>Fortnite: 144+ FPS at High settings</li>
                                <li>Valorant: 200+ FPS at High settings</li>
                                <li>Cyberpunk 2077: 60+ FPS at Medium settings</li>
                                <li>Call of Duty: Warzone: 100+ FPS at Medium settings</li>
                                <li>CS2: 200+ FPS at High settings</li>
                            </ul>
                        </div>
                        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                            <div>
                                <p className="font-medium mb-2">Pros:</p>
                                <ul className="list-disc pl-6 space-y-1 text-green-600 dark:text-green-400">
                                    <li>Great value for 1080p gaming</li>
                                    <li>Solid upgrade path</li>
                                    <li>Good for streaming at 1080p</li>
                                    <li>Handles content creation</li>
                                </ul>
                            </div>
                            <div>
                                <p className="font-medium mb-2">Cons:</p>
                                <ul className="list-disc pl-6 space-y-1 text-red-600 dark:text-red-400">
                                    <li>Limited 1440p performance</li>
                                    <li>Basic cooling solution</li>
                                    <li>Entry-level PSU</li>
                                </ul>
                            </div>
                        </div>
                        <div>
                            <p className="font-medium">Perfect For:</p>
                            <ul className="list-disc pl-6 space-y-1">
                                <li>First-time PC gamers</li>
                                <li>Competitive esports titles</li>
                                <li>1080p streaming</li>
                                <li>Light content creation</li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div className="border p-6 rounded-lg space-y-6">
                    <h3 className="text-xl font-semibold">Mid-Range Gaming ($1500-1700)</h3>
                    <div className="space-y-4">
                        <div>
                            <p className="font-medium">Specifications:</p>
                            <ul className="list-disc pl-6 space-y-1">
                                <li>CPU: Intel Core i7-13700K</li>
                                <li>GPU: NVIDIA RTX 4070</li>
                                <li>RAM: 32GB DDR5-6000</li>
                                <li>Storage: 1TB NVMe SSD + 2TB HDD</li>
                                <li>PSU: 850W 80+ Gold</li>
                                <li>Cooling: 240mm AIO liquid cooler + 4 RGB fans</li>
                            </ul>
                        </div>
                        <div>
                            <p className="font-medium">Gaming Performance:</p>
                            <ul className="list-disc pl-6 space-y-1">
                                <li>1440p Gaming:</li>
                                <ul className="list-circle pl-6">
                                    <li>Fortnite: 240+ FPS at High settings</li>
                                    <li>Valorant: 400+ FPS at High settings</li>
                                    <li>Cyberpunk 2077: 100+ FPS at High with DLSS</li>
                                    <li>Call of Duty: Warzone: 144+ FPS at High settings</li>
                                    <li>CS2: 400+ FPS at High settings</li>
                                </ul>
                            </ul>
                        </div>
                        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                            <div>
                                <p className="font-medium mb-2">Pros:</p>
                                <ul className="list-disc pl-6 space-y-1 text-green-600 dark:text-green-400">
                                    <li>Excellent 1440p performance</li>
                                    <li>Strong multitasking capability</li>
                                    <li>Quality cooling solution</li>
                                    <li>Future-proof components</li>
                                </ul>
                            </div>
                            <div>
                                <p className="font-medium mb-2">Cons:</p>
                                <ul className="list-disc pl-6 space-y-1 text-red-600 dark:text-red-400">
                                    <li>Higher power consumption</li>
                                    <li>Premium price point</li>
                                    <li>May need better cooling for OC</li>
                                </ul>
                            </div>
                        </div>
                        <div>
                            <p className="font-medium">Perfect For:</p>
                            <ul className="list-disc pl-6 space-y-1">
                                <li>Serious gamers</li>
                                <li>Content creators</li>
                                <li>Streamers</li>
                                <li>Multi-monitor setups</li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div className="border p-6 rounded-lg space-y-6">
                    <h3 className="text-xl font-semibold">High-End Gaming ($2500+)</h3>
                    <div className="space-y-4">
                        <div>
                            <p className="font-medium">Specifications:</p>
                            <ul className="list-disc pl-6 space-y-1">
                                <li>CPU: Intel Core i9-14900K</li>
                                <li>GPU: NVIDIA RTX 4090</li>
                                <li>RAM: 64GB DDR5-6400</li>
                                <li>Storage: 2TB NVMe SSD + 4TB HDD</li>
                                <li>PSU: 1000W 80+ Platinum</li>
                                <li>Cooling: 360mm AIO liquid cooler + 6 RGB fans</li>
                                <li>Case: Premium airflow-focused with tempered glass</li>
                            </ul>
                        </div>
                        <div>
                            <p className="font-medium">Gaming Performance:</p>
                            <ul className="list-disc pl-6 space-y-1">
                                <li>4K Gaming:</li>
                                <ul className="list-circle pl-6">
                                    <li>Fortnite: 240+ FPS at High settings</li>
                                    <li>Valorant: 600+ FPS at High settings</li>
                                    <li>Cyberpunk 2077: 100+ FPS at Ultra with Ray Tracing</li>
                                    <li>Call of Duty: Warzone: 200+ FPS at High settings</li>
                                    <li>CS2: 600+ FPS at High settings</li>
                                </ul>
                            </ul>
                        </div>
                        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                            <div>
                                <p className="font-medium mb-2">Pros:</p>
                                <ul className="list-disc pl-6 space-y-1 text-green-600 dark:text-green-400">
                                    <li>Ultimate 4K gaming experience</li>
                                    <li>Top-tier ray tracing performance</li>
                                    <li>Professional-grade content creation</li>
                                    <li>Premium build quality</li>
                                </ul>
                            </div>
                            <div>
                                <p className="font-medium mb-2">Cons:</p>
                                <ul className="list-disc pl-6 space-y-1 text-red-600 dark:text-red-400">
                                    <li>Very high cost</li>
                                    <li>High power consumption</li>
                                    <li>Overkill for most users</li>
                                </ul>
                            </div>
                        </div>
                        <div>
                            <p className="font-medium">Perfect For:</p>
                            <ul className="list-disc pl-6 space-y-1">
                                <li>4K/high refresh rate gaming</li>
                                <li>Professional content creation</li>
                                <li>Machine learning/AI development</li>
                                <li>Virtual reality development</li>
                                <li>Future-proof gaming setup</li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div className="p-4 rounded-lg mt-8">
                    <h3 className="text-lg font-semibold mb-3">Additional Considerations</h3>
                    <ul className="list-disc pl-6 space-y-2">
                        <li>Check warranty terms and duration</li>
                        <li>Research the system integrator's reputation</li>
                        <li>Look for reviews of the specific model</li>
                        <li>Verify upgrade and maintenance options</li>
                        <li>Consider included software and bloatware</li>
                        <li>Check for sales and seasonal discounts</li>
                    </ul>
                </div>
            </section>
        </div>
    )
}