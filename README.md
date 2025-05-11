# ABCD Smart Home Automation System

## Project Overview

The **ABCD Smart Home Automation System** is a console-based application designed to simulate a smart home environment. The system features a main control panel that manages various smart devices and users, allowing for flexible automation and notification handling. No GUI or database is used; all interactions occur via the console.

## Project Scope

- **Single Main Control Panel**: Only one instance exists at a time (Singleton pattern with lazy initialization).
- **Smart Devices**: Initially includes a smart thermostat, smart air conditioner, smart light, and smart camera. Each device can be turned on/off and has device-specific commands.
- **User Management**: Supports multiple users (admin, registered, and unregistered). Admin has full access; registered users receive notifications based on mode; unregistered users do not receive notifications.
- **Modes of Operation**: The control panel can operate in multiple modes (Away, Pet, Active), affecting notification behavior.
- **Extensible Design**: New devices and features can be added with minimal changes to the core logic.

## UML Class Diagram
![Untitled Diagram drawio](https://github.com/user-attachments/assets/0418791d-3988-4161-b7fe-e803874c39fe)

## Features

- Turn all devices on/off from the control panel
- Device-specific commands (e.g., change temperature, adjust fan speed, change resolution, change brightness)
- User registration and notification preferences
- Mode switching (Away, Pet, Active) with dynamic notification strategies
- Real-time notifications to users based on device events and current mode

## Design Patterns Used

### 1. Singleton (with Lazy Initialization)

- **Purpose**: Ensures only one instance of the Main Control Panel exists, created only when first needed.
- **Scenario**: The control panel is the central hub for the entire home and must be unique.

### 2. Factory Method

- **Purpose**: Encapsulates the creation of smart devices, allowing for easy addition of new device types.
- **Scenario**: Devices are created by a dedicated factory after the control panel is initialized.

### 3. Iterator

- **Purpose**: Allows sequential traversal of devices and users for bulk operations (e.g., turning all devices on/off, sending notifications).
- **Scenario**: The control panel iterates through device and user lists to perform actions consistently.

### 4. Observer

- **Purpose**: Implements a notification system where users can register/unregister for device notifications.
- **Scenario**: Registered users receive notifications based on mode; admin always receives all notifications.

### 5. Command

- **Purpose**: Encapsulates user actions (e.g., turn all devices on/off) as command objects, decoupling request from execution.
- **Scenario**: Each action is a command object with an `execute()` method, allowing for flexible action management.

### 6. Strategy

- **Purpose**: Allows the control panel to switch between different notification algorithms (modes) at runtime.
- **Scenario**: Modes (Active, Away, Pet) are implemented as strategies, each handling notifications differently.

## Usage

1. **Start the Application**: The control panel is initialized on first use.
2. **Device Management**: Devices are created via the factory and managed by the control panel.
3. **User Management**: Users can register, unregister, and set notification preferences.
4. **Mode Switching**: Users can switch between Active, Away, and Pet modes to change notification behavior.
5. **Command Execution**: Actions like turning all devices on/off are executed via command objects.

## Example Scenarios

- In **Away Mode**, if the smart camera detects movement, all registered users are notified.
- In **Pet Mode**, notifications are sent for movement except when a pet is detected.
- In **Active Mode**, notifications are suppressed for all except the admin.

## Extending the System

- **Add New Devices**: Implement a new device class and update the factory.
- **Add New Modes**: Implement a new strategy class and integrate it with the control panel.

## Project Structure

- `main/` - Main application logic and entry point
- `controlPanel/` - Control panel (Singleton)
- `devices/` - Smart device classes
- `factory/` - Device factory (Factory Method)
- `iterator/` - Iterators for devices and users
- `users/` - User management and observer logic
- `command/` - Command pattern implementations
- `strategy/` - Notification strategies (modes)

---

This project demonstrates the use of multiple software design patterns in a practical smart home automation scenario, focusing on extensibility, maintainability, and clear separation of concerns.
