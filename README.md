# Birthday Card App

A delightful Android application built with Jetpack Compose that creates animated birthday cards with interactive visual elements.

## Features

- Dynamic color-changing animations
- Floating balloons with smooth animations
- Rotating confetti particles
- Customizable recipient name
- Gradient background effects
- Material 3 design components
- Edge-to-edge display support

## Technical Details

### Built With

- Kotlin
- Jetpack Compose
- Material 3 Components
- Compose Animation APIs

### Key Components

1. **BirthdayCard Composable**

   - Main component that renders the birthday card
   - Features animated color transitions
   - Implements balloon animations
   - Displays customizable text elements

2. **Confetti Composable**
   - Creates rotating particle effects
   - Uses infinite transitions for continuous animation
   - Generates random colors and positions

## Getting Started

### Prerequisites

- Android Studio Arctic Fox or newer
- Minimum SDK Version: Supports Material 3
- Kotlin 1.8.0 or higher

### Installation

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the app on an emulator or physical device

## Usage

To create a birthday card, use the `BirthdayCard` composable:

```kotlin
BirthdayCard(
    name = "Recipient Name",
    modifier = Modifier.padding(paddingValues)
)
```

### Customization

You can modify the following elements:

- Card colors in the `colors` list
- Animation durations in the transition specifications
- Text styles and font sizes
- Balloon and confetti properties

## Preview

The app includes a preview function (`PreviewBirthdayCard`) that shows how the card looks with sample data.

```kotlin
BirthdayCard(
    name = "Recipient Name",
    modifier = Modifier.padding(paddingValues)
)
```
