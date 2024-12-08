<h1 align="center">Minimizing Costs - Android</h1>

---

<h2>Description</h2>
<p>
This is a business management simulation application where the goal is to minimize costs and maximize revenue. It uses Artificial Intelligence (AI) to make automated decisions in a fictional business environment, based on <strong>Thompson Sampling</strong> for probabilistic decision-making.
</p>
<p>
The application simulates scenarios like stock control, promotions, production, and financial management, combining both manual and automated decisions.
</p>
<p>
While the AI approach leverages probabilistic models to maximize efficiency, a manual strategy simulates traditional decision-making to compare with the AI-driven approach.
</p>
<p>
The application uses <strong>MVVM architecture</strong> and follows the principles of <strong>Clean Code</strong> to ensure maintainability and scalability.
</p>

---

<h2>Application Features</h2>
<ul>
  <li><strong>Business Simulation:</strong> Simulates revenue management by controlling fixed and variable costs, sales, promotions, and stock levels.</li>
  <li><strong>AI-based Decisions:</strong> Uses <strong>Thompson Sampling</strong> to automate decisions and maximize revenue.</li>
  <li><strong>Manual Strategy:</strong> Allows for manual adjustments in pricing, stock, and promotions to simulate traditional business strategies.</li>
  <li><strong>Alerts:</strong> Provides real-time alerts for low stock levels and critical cash flow issues.</li>
  <li><strong>UI with Jetpack Compose:</strong> Modern UI components for interaction, including sliders for adjustments and charts for performance tracking.</li>
</ul>

---

<h3>1. Application Structure</h3>
<p>The program follows the MVVM architecture:</p>
<ul>
  <li><strong>View (UI):</strong> User interface built with Jetpack Compose for modern, reactive design.</li>
  <li><strong>ViewModel:</strong> Contains the business logic and manages the state of the simulation.</li>
  <li><strong>Model:</strong> Stores data such as stock levels, revenue, and customer counts, and handles calculations for decisions.</li>
</ul>

<h3>2. Simulation with Strategies</h3>
<p>The app simulates business decision-making using two strategies:</p>
<ul>
  <li><strong>Thompson Sampling:</strong> A probabilistic model used to make data-driven decisions based on past results.</li>
  <li><strong>Manual Decisions:</strong> Allows users to adjust pricing, stock levels, and promotions based on traditional strategies.</li>
</ul>

<h3>3. Key Components</h3>
<ul>
  <li>
    <strong>BusinessEnvironment:</strong> Manages all variables related to the business environment, including costs, revenue, and stock.
  </li>
  <li>
    <strong>BusinessViewModel:</strong> Manages the state of the business simulation and updates the environment after each decision.
  </li>
  <li>
    <strong>UI (Jetpack Compose):</strong> Contains interactive components:
    <ul>
      <li>A <strong>Start Simulation</strong> button to begin the process.</li>
      <li>A <strong>Progress Bar</strong> to show real-time updates during simulation.</li>
      <li>Charts using <strong>MPAndroidChart</strong> to visualize the business performance.</li>
    </ul>
  </li>
</ul>

<h3>4. Execution Flow</h3>
<ol>
  <li>User interacts with the app by adjusting stock, pricing, and promotions.</li>
  <li>The app runs simulations using the two strategies (AI-based and manual).</li>
  <li>The progress bar updates in real-time to show the simulation’s progress.</li>
  <li>Upon completion, results are displayed with visual graphs to compare strategies.</li>
</ol>

<h3>5. Key Concepts</h3>
<ul>
  <li><strong>Thompson Sampling:</strong> A method of balancing exploration and exploitation for decision-making in uncertain environments.</li>
  <li><strong>Beta Distribution:</strong> A probability distribution used to model uncertainty and guide decision-making.</li>
  <li><strong>Cost Management:</strong> The simulation helps identify ways to minimize costs while maximizing revenue.</li>
</ul>

---

<h2>Project Setup</h2>
<h3>1. Prerequisites</h3>
<ul>
  <li>Android Studio version <strong>2024.2.1 Ladybug</strong> or higher.</li>
  <li>Android device or emulator with <strong>API 24</strong> or higher.</li>
</ul>

<h3>2. Clone the Repository</h3>
<pre><code>git clone https://github.com/calbertobarbosajr/Minimizing-Costs.git</code></pre>

<h3>3. Gradle Configuration</h3>
<h4><code>settings.gradle</code></h4>
<p>Add the required repositories:</p>
<pre><code>dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://maven.google.com")
            url = uri("https://jitpack.io")
        }
    }
}</code></pre>

<h4><code>build.gradle</code> (App Module)</h4>
<p>Include the following dependencies:</p>
<pre><code>android {
    namespace = "com.calberto_barbosa_jr.minimizingcosts"
    compileSdk = 35
}

dependencies {
implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")
implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0")
}</code></pre>

<h3>4. Build and Run</h3>
<ul>
  <li>Connect a device or start an emulator.</li>
  <li>Click <strong>Run</strong> to install and execute the app.</li>
</ul>

---

<h2>License</h2>
<p>
This project is licensed under the <strong>MIT License</strong>. For more details, refer to the 
<a href="https://www.mit.edu/~amini/LICENSE.md">LICENSE</a> file.
</p>
