<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Minimizing Costs - Documentation</title>
</head>
<body>
    <h1>Minimizing Costs</h1>

    <h2>Description</h2>
    <p>
        This is an application for simulating business management, aiming to minimize costs and maximize revenues. 
        It uses Artificial Intelligence (AI) to make automated decisions in a fictional business environment, built with 
        the <strong>MVVM architecture</strong> and <strong>Clean Code principles</strong>.
    </p>
    <p>
        The program combines manual and automated decisions, simulating scenarios such as inventory control, promotions, 
        production, and financial management, with techniques like <strong>Thompson Sampling</strong>.
    </p>

    <h2>Main Features</h2>
    <ul>
        <li><strong>Business Simulation:</strong> Manage fixed and variable costs, inventory control, and promotions' impact on customer numbers.</li>
        <li><strong>AI and Non-AI Decisions:</strong>
            <ul>
                <li>AI Decisions: Automated decisions using probabilistic learning to maximize efficiency.</li>
                <li>Non-AI Decisions: Fixed strategies, simulating traditional management approaches.</li>
            </ul>
        </li>
        <li><strong>User Interaction:</strong> Adjust selling price, production cost, inventory restocking, and promotion impacts.</li>
        <li><strong>Visual Alerts:</strong>
            <ul>
                <li><strong>Low Stock:</strong> Triggered when inventory falls below 20 units.</li>
                <li><strong>Critical Cash Flow:</strong> Warns when costs exceed 80% of revenue.</li>
            </ul>
        </li>
        <li><strong>Jetpack Compose Interface:</strong> Real-time display of results with charts and performance indicators.</li>
        <li><strong>Game Over Rules:</strong>
            <ul>
                <li>Inventory depletion.</li>
                <li>Total costs exceeding 3x the revenue.</li>
            </ul>
        </li>
    </ul>

    <h2>Architecture and Components</h2>
    <ul>
        <li><strong>BusinessEnvironment:</strong> Represents the business environment, including inventory, costs, sales, and customers. Implements logic for automatic restocking and AI-based promotions.</li>
        <li><strong>BusinessViewModel:</strong> Manages the business environment's state and updates it after each interaction.</li>
        <li><strong>Jetpack Compose Interface:</strong> Provides interactive elements like buttons, sliders, and real-time financial reports.</li>
    </ul>

    <h2>Project Setup</h2>

    <h3>1. Prerequisites</h3>
    <ul>
        <li>Android Studio version <strong>2024.2.1 Ladybug</strong> or higher.</li>
        <li>Android device or emulator with <strong>API 24</strong> or higher.</li>
    </ul>

    <h3>2. Clone the Repository</h3>
    <pre>
        <code>git clone https://github.com/calbertobarbosajr/Minimizing-Costs.git</code>
    </pre>

    <h3>3. Gradle Configuration</h3>
    <p><strong>File <code>settings.gradle</code>:</strong></p>
    <pre>
        <code>
dependencyResolutionManagement {
repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
repositories {
google()
mavenCentral()
maven {
url = uri("https://maven.google.com")
url = uri("https://jitpack.io")
}
}    
}
</code>
</pre>

    <p><strong>File <code>build.gradle</code> (App Module):</strong></p>
    <pre>
        <code>
android {
namespace = "com.calberto_barbosa_jr.minimizingcosts"
compileSdk = 35
}

dependencies {
implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")
implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0")
}
</code>
</pre>

    <h3>4. Build and Run</h3>
    <ul>
        <li>Connect a device or start an emulator.</li>
        <li>Click on <strong>Run</strong> to install and execute the app.</li>
    </ul>

    <h2>Program Usage</h2>
    <ol>
        <li>
            <strong>Initialization:</strong> The program initializes the business environment with default values, 
            such as 100 initial customers and 500 inventory units.
        </li>
        <li>
            <strong>Automatic Restocking:</strong> Inventory is monitored and automatically restocked when it falls 
            below 20 units. The restocking quantity is adjustable via slider.
        </li>
        <li>
            <strong>Promotions and Thompson Sampling:</strong> The AI uses Thompson Sampling to decide on effective promotions, directly impacting customer numbers and revenue.
        </li>
        <li>
            <strong>Alerts and Adjustments:</strong> Adjust parameters (price, production cost, etc.) in response to visual alerts.
        </li>
    </ol>

    <h2>License</h2>
    <p>
        This project is licensed under the <a href="https://www.mit.edu/~amini/LICENSE.md" target="_blank">MIT License</a>.
    </p>
</body>
</html>
