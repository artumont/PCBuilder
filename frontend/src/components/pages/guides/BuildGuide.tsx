'use client'

export default function BuildGuide() {
    return (
        <div className="space-y-8">
            <h2 className="text-2xl font-bold">How to Build a PC</h2>
            
            <section className="space-y-4">
                <h3 className="text-xl font-semibold">1. Planning Your Build</h3>
                <p>Before purchasing components, consider:</p>
                <ul className="list-disc pl-6 space-y-2">
                    <li>Your budget - Plan to spend more on critical components like CPU and GPU</li>
                    <li>Primary use - Gaming, content creation, office work, etc.</li>
                    <li>Future upgradability - Consider a motherboard that supports future CPU upgrades</li>
                    <li>Monitor requirements - Resolution and refresh rate affect GPU choice</li>
                    <li>Workspace setup - Ensure proper ventilation and power delivery</li>
                </ul>
                <div className="mt-4 p-4 rounded-lg">
                    <p className="font-medium mb-2">Budget Distribution Tips:</p>
                    <ul className="list-disc pl-6 space-y-1">
                        <li>GPU: 30-40% of budget for gaming builds</li>
                        <li>CPU: 20-25% of budget</li>
                        <li>RAM + Storage: 15-20% of budget</li>
                        <li>Motherboard: 10-15% of budget</li>
                        <li>PSU + Case + Cooling: Remaining budget</li>
                    </ul>
                </div>
            </section>

            <section className="space-y-4">
                <h3 className="text-xl font-semibold">2. Essential Components</h3>
                <ul className="list-disc pl-6 space-y-2">
                    <li><strong>CPU (Processor)</strong> - The brain of your computer
                        <ul className="list-circle pl-6 mt-1 text-sm text-gray-600 dark:text-gray-400">
                            <li>Consider core count for multitasking</li>
                            <li>Check socket compatibility with motherboard</li>
                            <li>Factor in integrated graphics if skipping GPU</li>
                        </ul>
                    </li>
                    <li><strong>Motherboard</strong> - Connects all components together
                        <ul className="list-circle pl-6 mt-1 text-sm text-gray-600 dark:text-gray-400">
                            <li>Verify CPU socket and chipset compatibility</li>
                            <li>Check RAM speed and capacity support</li>
                            <li>Consider I/O ports and expansion needs</li>
                        </ul>
                    </li>
                    <li><strong>RAM</strong> - Memory for running programs
                        <ul className="list-circle pl-6 mt-1 text-sm text-gray-600 dark:text-gray-400">
                            <li>16GB minimum for gaming</li>
                            <li>32GB+ for content creation</li>
                            <li>Match speed with CPU/motherboard support</li>
                        </ul>
                    </li>
                    <li><strong>Storage</strong> - For OS and data
                        <ul className="list-circle pl-6 mt-1 text-sm text-gray-600 dark:text-gray-400">
                            <li>NVMe SSD for OS and programs</li>
                            <li>SATA SSD for games</li>
                            <li>HDD for mass storage</li>
                        </ul>
                    </li>
                    <li><strong>GPU (Graphics Card)</strong> - Handles graphics processing
                        <ul className="list-circle pl-6 mt-1 text-sm text-gray-600 dark:text-gray-400">
                            <li>Match with monitor resolution/refresh rate</li>
                            <li>Consider VRAM for higher resolutions</li>
                            <li>Check power supply requirements</li>
                        </ul>
                    </li>
                    <li><strong>PSU (Power Supply)</strong> - Powers all components
                        <ul className="list-circle pl-6 mt-1 text-sm text-gray-600 dark:text-gray-400">
                            <li>Choose 80+ Gold or better efficiency</li>
                            <li>Add 100W to calculated power needs</li>
                            <li>Modular for easier cable management</li>
                        </ul>
                    </li>
                    <li><strong>Case</strong> - Houses all components
                        <ul className="list-circle pl-6 mt-1 text-sm text-gray-600 dark:text-gray-400">
                            <li>Check motherboard form factor support</li>
                            <li>Verify GPU length clearance</li>
                            <li>Consider dust filters and airflow</li>
                        </ul>
                    </li>
                    <li><strong>CPU Cooler</strong> - Keeps processor temperatures in check
                        <ul className="list-circle pl-6 mt-1 text-sm text-gray-600 dark:text-gray-400">
                            <li>Match TDP with CPU requirements</li>
                            <li>Check clearance in case</li>
                            <li>Consider noise levels</li>
                        </ul>
                    </li>
                </ul>
            </section>

            <section className="space-y-4">
                <h3 className="text-xl font-semibold">3. Assembly Guide</h3>
                <div className="space-y-6">
                    <div>
                        <h4 className="font-semibold mb-2">Preparation:</h4>
                        <ul className="list-disc pl-6 space-y-2">
                            <li>Work on a static-free surface</li>
                            <li>Organize parts and screws</li>
                            <li>Keep manuals handy</li>
                            <li>Have proper tools ready (screwdrivers, thermal paste)</li>
                        </ul>
                    </div>
                    <div>
                        <h4 className="font-semibold mb-2">Step-by-Step Assembly:</h4>
                        <ol className="list-decimal pl-6 space-y-2">
                            <li>Install CPU into motherboard (align arrows)</li>
                            <li>Apply thermal paste (pea-sized dot)</li>
                            <li>Mount CPU cooler</li>
                            <li>Install RAM (check motherboard slots)</li>
                            <li>Install M.2 SSDs if using</li>
                            <li>Install motherboard standoffs in case</li>
                            <li>Mount motherboard in case</li>
                            <li>Install PSU and route cables</li>
                            <li>Install GPU and other PCIe cards</li>
                            <li>Connect all power cables</li>
                            <li>Install case fans and storage drives</li>
                            <li>Connect front panel and USB headers</li>
                        </ol>
                    </div>
                    <div>
                        <h4 className="font-semibold mb-2">Cable Management Tips:</h4>
                        <ul className="list-disc pl-6 space-y-2">
                            <li>Route cables behind motherboard tray</li>
                            <li>Use case tie points and zip ties</li>
                            <li>Group cables by destination</li>
                            <li>Leave some slack for future changes</li>
                        </ul>
                    </div>
                </div>
            </section>

            <section className="space-y-4">
                <h3 className="text-xl font-semibold">4. Post-Build Setup</h3>
                <div className="space-y-6">
                    <div>
                        <h4 className="font-semibold mb-2">BIOS Configuration:</h4>
                        <ul className="list-disc pl-6 space-y-2">
                            <li>Enable XMP/DOCP profile for RAM</li>
                            <li>Set boot drive priority</li>
                            <li>Check CPU and case fan curves</li>
                            <li>Verify all components are recognized</li>
                        </ul>
                    </div>
                    <div>
                        <h4 className="font-semibold mb-2">Windows Installation:</h4>
                        <ul className="list-disc pl-6 space-y-2">
                            <li>Create bootable USB using Media Creation Tool</li>
                            <li>Install Windows on NVMe/SSD drive</li>
                            <li>Install motherboard and GPU drivers</li>
                            <li>Run Windows Update</li>
                        </ul>
                    </div>
                    <div>
                        <h4 className="font-semibold mb-2">Testing:</h4>
                        <ul className="list-disc pl-6 space-y-2">
                            <li>Monitor temperatures under load</li>
                            <li>Run memory stability test</li>
                            <li>Benchmark CPU and GPU</li>
                            <li>Test all USB ports and features</li>
                        </ul>
                    </div>
                </div>
            </section>

            <section className="space-y-4">
                <h3 className="text-xl font-semibold">5. Troubleshooting</h3>
                <div className="space-y-4">
                    <div className="p-4 rounded-lg">
                        <p className="font-medium mb-2">No Power/Won't Boot:</p>
                        <ul className="list-disc pl-6 space-y-1">
                            <li>Check PSU switch and power cable</li>
                            <li>Verify 24-pin and CPU power connections</li>
                            <li>Reseat RAM modules</li>
                            <li>Clear CMOS if necessary</li>
                        </ul>
                    </div>
                    <div className="p-4 rounded-lg">
                        <p className="font-medium mb-2">Display Issues:</p>
                        <ul className="list-disc pl-6 space-y-1">
                            <li>Verify monitor input source</li>
                            <li>Try different cable or port</li>
                            <li>Reseat GPU and power cables</li>
                            <li>Test with integrated graphics if available</li>
                        </ul>
                    </div>
                    <div className="p-4 rounded-lg">
                        <p className="font-medium mb-2">Performance Issues:</p>
                        <ul className="list-disc pl-6 space-y-1">
                            <li>Check CPU and GPU temperatures</li>
                            <li>Verify RAM is running at rated speed</li>
                            <li>Update all drivers</li>
                            <li>Check Windows power plan settings</li>
                        </ul>
                    </div>
                </div>
            </section>
        </div>
    )
}
