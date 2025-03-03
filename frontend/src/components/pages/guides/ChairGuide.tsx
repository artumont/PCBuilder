'use client'

export default function ChairGuide() {
    return (
        <div className="space-y-8">
            <h2 className="text-2xl font-bold">Choosing the Perfect Gaming Chair</h2>

            <section className="space-y-4">
                <h3 className="text-xl font-semibold">Essential Features</h3>
                <div className="space-y-6">
                    <div className="p-4 rounded-lg">
                        <p className="font-medium mb-2">Ergonomic Support:</p>
                        <ul className="list-disc pl-6 space-y-2">
                            <li><strong>Lumbar Support</strong> - Adjustable lower back support</li>
                            <li><strong>Neck Pillow</strong> - Removable cushion for neck support</li>
                            <li><strong>Armrests</strong> - 3D or 4D adjustable for proper arm positioning</li>
                            <li><strong>Seat Height</strong> - Gas lift for height adjustment</li>
                            <li><strong>Backrest</strong> - 90-165° tilt range for different activities</li>
                        </ul>
                    </div>

                    <div className="p-4 rounded-lg">
                        <p className="font-medium mb-2">Materials:</p>
                        <ul className="list-disc pl-6 space-y-2">
                            <li><strong>PU Leather</strong> - Durable, easy to clean, less breathable</li>
                            <li><strong>Fabric</strong> - More breathable, harder to clean</li>
                            <li><strong>Mesh</strong> - Best airflow, durable but less cushioning</li>
                            <li><strong>Genuine Leather</strong> - Premium feel, expensive, requires maintenance</li>
                        </ul>
                    </div>

                    <div className="p-4 rounded-lg">
                        <p className="font-medium mb-2">Build Quality:</p>
                        <ul className="list-disc pl-6 space-y-2">
                            <li><strong>Frame Material</strong> - Steel frame for durability</li>
                            <li><strong>Base</strong> - Metal base preferred over plastic</li>
                            <li><strong>Wheels</strong> - PU-coated for smooth rolling</li>
                            <li><strong>Weight Capacity</strong> - Check rated capacity</li>
                        </ul>
                    </div>
                </div>
            </section>

            <section className="space-y-4">
                <h3 className="text-xl font-semibold">Chair Types</h3>
                <div className="space-y-6">
                    <div className="border p-4 rounded-lg">
                        <h4 className="font-semibold mb-2">Racing-Style Gaming Chairs</h4>
                        <ul className="list-disc pl-6 space-y-1">
                            <li>Aggressive aesthetic design</li>
                            <li>High backrest with shoulder support</li>
                            <li>Usually includes neck and lumbar pillows</li>
                            <li>Price range: $150-500</li>
                            <li>Best for: Traditional gaming setups</li>
                        </ul>
                    </div>

                    <div className="border p-4 rounded-lg">
                        <h4 className="font-semibold mb-2">Ergonomic Office Chairs</h4>
                        <ul className="list-disc pl-6 space-y-1">
                            <li>Professional appearance</li>
                            <li>Superior ergonomic features</li>
                            <li>Better for long-term comfort</li>
                            <li>Price range: $300-1500+</li>
                            <li>Best for: Work-from-home/gaming combo</li>
                        </ul>
                    </div>

                    <div className="border p-4 rounded-lg">
                        <h4 className="font-semibold mb-2">Premium Gaming Chairs</h4>
                        <ul className="list-disc pl-6 space-y-1">
                            <li>High-end materials and build quality</li>
                            <li>Advanced ergonomic features</li>
                            <li>Extended warranty coverage</li>
                            <li>Price range: $400-1000</li>
                            <li>Best for: Serious gamers, streamers</li>
                        </ul>
                    </div>
                </div>
            </section>

            <section className="space-y-4">
                <h3 className="text-xl font-semibold">Size and Fit Guide</h3>
                <div className="space-y-4">
                    <ul className="list-disc pl-6 space-y-2">
                        <li><strong>Seat Width</strong> - Should be wider than your hips with 1 inch on each side</li>
                        <li><strong>Seat Depth</strong> - Leave 2-4 inches between knee and seat edge</li>
                        <li><strong>Backrest Height</strong> - Should support entire back and neck</li>
                        <li><strong>Height Range</strong> - Feet should rest flat on floor</li>
                    </ul>
                    <div className="p-4 rounded-lg mt-4">
                        <p className="font-medium mb-2">Size Recommendations:</p>
                        <ul className="list-disc pl-6 space-y-1">
                            <li>5'5" and under: Small/Standard size</li>
                            <li>5'5" to 6'0": Standard size</li>
                            <li>6'0" to 6'4": Large size</li>
                            <li>6'4" and up: XL/Big and Tall models</li>
                        </ul>
                    </div>
                </div>
            </section>

            <section className="space-y-4">
                <h3 className="text-xl font-semibold">Setup and Maintenance</h3>
                <div className="space-y-4">
                    <div>
                        <h4 className="font-semibold mb-2">Optimal Setup:</h4>
                        <ul className="list-disc pl-6 space-y-2">
                            <li>Keep elbows at 90-110 degrees when typing</li>
                            <li>Monitor at eye level or slightly below</li>
                            <li>Feet flat on floor or footrest</li>
                            <li>Knees at or slightly below hip level</li>
                        </ul>
                    </div>
                    <div>
                        <h4 className="font-semibold mb-2">Maintenance Tips:</h4>
                        <ul className="list-disc pl-6 space-y-2">
                            <li>Clean regularly according to material type</li>
                            <li>Check and tighten screws monthly</li>
                            <li>Lubricate moving parts every 6 months</li>
                            <li>Keep away from direct sunlight</li>
                            <li>Follow weight capacity guidelines</li>
                        </ul>
                    </div>
                </div>
            </section>
        </div>
    )
}
