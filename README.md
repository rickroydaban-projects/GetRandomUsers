# GetRandomUsers

**GetRandomUsers** is a Kotlin-based Android application that retrieves a random list of users from an external API. This app showcases best practices in modern Android development, incorporating tools like Jetpack Compose, Kotlin Coroutines, and modern architecture principles to manage network requests, data handling, and UI updates efficiently.

## Features

- **Fetch Random Users**: Uses the [Random User API](https://randomuser.me/) to fetch a list of users.
- **Debounced Search**: Efficient user search functionality with debouncing to limit unnecessary network requests.
- **Jetpack Compose UI**: Modern UI design using Jetpack Compose to create dynamic and declarative interfaces.
- **MVVM + MVI Architecture**: Clean architecture pattern combining MVVM with MVI for managing UI state.
- **Theming**: Switch between light mode and dark mode.
- **Connection Handler**: Enable/disable network connectivity to test connection states
- **Offline Support**: Caching mechanism using Room to store the user list for offline access.
- **Coroutines & Flows**: Handles asynchronous tasks efficiently with Kotlin Coroutines and StateFlow for managing UI state.

## Screenshots

| Home Screen | User Details | Setting |
|-------------|--------------|--------------|
| ![Home Screen](http://collabturf.com/screens/home.jpg) | ![User Details](http://collabturf.com/screens/detail.jpg) | ![Setting Screen](http://collabturf.com/screens/theme.jpg)

## Architecture

The project follows a **modular, clean architecture** design pattern, emphasizing separation of concerns. The main components include:

- **Presentation Layer**: Implements Jetpack Compose with ViewModels to manage UI state.
- **Data Layer**: Uses Retrofit for network requests, Room for local caching, and Repository pattern to mediate between the data sources.
- **Domain Layer**: Contains the business logic, making the code more testable and maintainable.

## Technologies & Libraries

- **Kotlin**: Primary language for the application.
- **Jetpack Compose**: Modern Android UI toolkit.
- **Retrofit**: For making network requests.
- **Room**: For local data persistence.
- **Coroutines & Flows**: For managing asynchronous tasks.
- **MockK**: For unit testing.
- **Google GSON**: For handling JSON parsing.
- **Dagger Hilt**: For dependency injection.

## How to Run the Project

1. Clone the repository:
    ```bash
    git clone git@github.com:rickroydaban-projects/GetRandomUsers.git
    ```
2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device.

## Testing

- Unit tests are written using JUnit and MockK to ensure the business logic is functioning as expected.
- To check for the test results:
    ```bash
    ./gradlew testDebugUnitTest
    ```
- After running the tests, you can find the results in the `build/reports/tests/testDebugUnitTest` directory. You can view the index.html file in a web browser to see a nicely formatted report of all tests, including their pass/fail status.

## Future Enhancements

- Add pagination support for large datasets.
- Implement push notifications for user-related updates.
- Improve the UI design for better user experience.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
