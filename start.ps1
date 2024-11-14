# Define parameter at the top of the script
param (
    [string]$ServiceName
)

# Определить исполняемый файл для gradlew в зависимости от платформы
$gradleCommand = if ($IsWindows) { ".\gradlew.bat" } else { "./gradlew" }

# Если не Windows, убедиться, что gradlew имеет права на выполнение
if (-not $IsWindows) {
    Write-Host "Ensuring gradlew is executable..."
    chmod +x ./gradlew
}

# Function to build and restart all services
function Build-And-Restart-All {
    Write-Host "Running gradlew build..."
    & $gradleCommand build
    if ($LASTEXITCODE -ne 0) {
        Write-Host "Gradle build failed. Exiting..."
        exit 1
    }

    Write-Host "Building Docker images for all services..."
    docker compose build
    if ($LASTEXITCODE -ne 0) {
        Write-Host "Docker compose build failed. Exiting..."
        exit 1
    }

    Write-Host "Starting all Docker containers..."
    docker compose up -d
    if ($LASTEXITCODE -ne 0) {
        Write-Host "Docker compose up failed."
        exit 1
    }

    Write-Host "All services are up and running."
}

# Function to build and restart a specific service
function Build-And-Restart-Service {
    param (
        [string]$service
    )

    Write-Host "Running gradlew build for service $service..."
    & $gradleCommand build
    if ($LASTEXITCODE -ne 0) {
        Write-Host "Gradle build failed for service $service. Exiting..."
        exit 1
    }

    Write-Host "Building Docker image for service $service..."
    docker compose build $service
    if ($LASTEXITCODE -ne 0) {
        Write-Host "Docker compose build failed for service $service. Exiting..."
        exit 1
    }

    Write-Host "Restarting Docker container for service $service..."
    docker compose up -d $service
    if ($LASTEXITCODE -ne 0) {
        Write-Host "Docker compose up failed for service $service."
        exit 1
    }

    Write-Host "Service $service is up and running."
}

# Check if a service name parameter was provided
if ($ServiceName) {
    # If a service name is provided, build and restart only that service
    Build-And-Restart-Service -service $ServiceName
} else {
    # If no service name is provided, build and restart all services
    Build-And-Restart-All
}
