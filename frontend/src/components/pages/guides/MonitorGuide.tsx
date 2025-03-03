'use client'

export default function MonitorGuide() {
    return (
        <div className="space-y-8">
            <h2 className="text-2xl font-bold">How to Choose a Gaming Monitor</h2>

            <section className="space-y-4">
                <h3 className="text-xl font-semibold">Key Specifications</h3>
                <div className="space-y-6">
                    <div className="p-4 rounded-lg">
                        <p className="font-medium mb-2">Resolution:</p>
                        <ul className="list-disc pl-6 space-y-2">
                            <li><strong>1080p (1920x1080)</strong> - Entry-level, good for competitive gaming</li>
                            <li><strong>1440p (2560x1440)</strong> - Sweet spot for gaming, balance of quality and performance</li>
                            <li><strong>4K (3840x2160)</strong> - Premium visual quality, requires powerful GPU</li>
                        </ul>
                    </div>

                    <div className="p-4 rounded-lg">
                        <p className="font-medium mb-2">Refresh Rate:</p>
                        <ul className="list-disc pl-6 space-y-2">
                            <li><strong>60Hz</strong> - Basic refresh rate, suitable for casual gaming</li>
                            <li><strong>144Hz</strong> - Excellent for most gamers, smoother motion</li>
                            <li><strong>240Hz</strong> - Competitive gaming, extremely fluid motion</li>
                            <li><strong>360Hz</strong> - Professional esports, minimal motion blur</li>
                        </ul>
                    </div>

                    <div className="p-4 rounded-lg">
                        <p className="font-medium mb-2">Panel Types:</p>
                        <ul className="list-disc pl-6 space-y-2">
                            <li><strong>TN</strong> - Fastest response times, poorest colors/viewing angles</li>
                            <li><strong>IPS</strong> - Best colors and viewing angles, good response times</li>
                            <li><strong>VA</strong> - Best contrast ratios, good colors, slower response times</li>
                            <li><strong>OLED</strong> - Perfect blacks, instant response, risk of burn-in</li>
                        </ul>
                    </div>
                </div>
            </section>

            <section className="space-y-4">
                <h3 className="text-xl font-semibold">Additional Features</h3>
                <ul className="list-disc pl-6 space-y-2">
                    <li><strong>Adaptive Sync</strong> - G-Sync (NVIDIA) or FreeSync (AMD) for tear-free gaming</li>
                    <li><strong>HDR Support</strong> - Better brightness and color range (look for HDR600 or better)</li>
                    <li><strong>Response Time</strong> - 1ms ideal for gaming, up to 5ms acceptable for casual use</li>
                    <li><strong>Ergonomics</strong> - Height, tilt, and pivot adjustments for comfort</li>
                    <li><strong>Ports</strong> - Multiple HDMI/DisplayPort for device connectivity</li>
                </ul>
            </section>

            <section className="space-y-4">
                <h3 className="text-xl font-semibold">Recommended Configurations</h3>
                
                <div className="space-y-6">
                    <div className="border p-4 rounded-lg">
                        <h4 className="font-semibold mb-2">Competitive Gaming</h4>
                        <ul className="list-disc pl-6 space-y-1">
                            <li>1080p or 1440p resolution</li>
                            <li>240Hz+ refresh rate</li>
                            <li>TN or Fast IPS panel</li>
                            <li>1ms response time</li>
                            <li>G-Sync/FreeSync support</li>
                            <li>Estimated cost: $300-500</li>
                        </ul>
                    </div>

                    <div className="border p-4 rounded-lg">
                        <h4 className="font-semibold mb-2">Visual Quality Gaming</h4>
                        <ul className="list-disc pl-6 space-y-1">
                            <li>4K resolution</li>
                            <li>144Hz refresh rate</li>
                            <li>IPS or OLED panel</li>
                            <li>HDR support (HDR600+)</li>
                            <li>G-Sync/FreeSync support</li>
                            <li>Estimated cost: $700-1000+</li>
                        </ul>
                    </div>

                    <div className="border p-4 rounded-lg">
                        <h4 className="font-semibold mb-2">Balanced Gaming</h4>
                        <ul className="list-disc pl-6 space-y-1">
                            <li>1440p resolution</li>
                            <li>144-165Hz refresh rate</li>
                            <li>IPS panel</li>
                            <li>1-4ms response time</li>
                            <li>G-Sync/FreeSync support</li>
                            <li>Estimated cost: $300-600</li>
                        </ul>
                    </div>
                </div>
            </section>

            <section className="space-y-4">
                <h3 className="text-xl font-semibold">Size and Setup Tips</h3>
                <ul className="list-disc pl-6 space-y-2">
                    <li><strong>24-inch</strong> - Ideal for 1080p and competitive gaming</li>
                    <li><strong>27-inch</strong> - Sweet spot for 1440p, good for most users</li>
                    <li><strong>32-inch</strong> - Better for 4K, ensure adequate desk space</li>
                    <li>View distance should be about 1.5-2x the screen diagonal</li>
                    <li>Consider mount compatibility for desk/wall mounting</li>
                    <li>Ensure proper lighting to reduce eye strain</li>
                </ul>
            </section>
        </div>
    )
}
