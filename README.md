# Project Readme

## API Connectivity (Atheesen)

The API Connectivity module, handled by Atheesen, focuses on establishing communication with the drone simulation server. Ensure that the API connection is robust and can fetch data seamlessly.

## Drone Dashboard (Victor)

The Drone Dashboard, developed by Victor, aims to provide a comprehensive view of drone information and coordinates. Follow these steps to achieve the desired functionality:

1. Load data from the Overview API for all drones.
2. Create a map with markers for each drone, allowing users to filter by status (ON/OFF).
3. Clicking on a drone marker should open a new window displaying the latest information for that specific drone.
4. Display the most recent DroneDynamics data for each drone.
5. Show the last 25 updates for each drone.
6. Implement a dynamic string to accommodate additional information in the future.

## Drone Catalog (Bahadir)

The Drone Catalog, managed by Bahadir, involves creating a graphical user interface (GUI) table displaying current available drones. Follow these steps:

1. Merge data from the Drones and DroneType APIs.
2. Split the string in the DroneType API output and match Manufacturer and TypeName to create a mixed table.
3. Recreate the table on each button press, considering changing variables.
4. Integrate DroneList (essentially Drone) and add DroneTypes.java for API link changes.

## Flight Dynamics (Wassabie)

The Flight Dynamics module, led by Wassabie, is responsible for displaying the flight behavior and parameters of each drone. Considerations include:

1. Load data only when the specific drone's window is open.
2. Display the most recent DroneDynamics data for each drone.
3. Clarify the concept of "dynamic presentation."
4. Utilize the provided API link for the latest data.

## Data Refresh

Implement an on-demand data refresh button. Keep user-opened windows active and update them upon button press. Experiment initially with two windows opened sequentially.

## Historical Analysis

This module, to be addressed at the end, involves analyzing historical data. Consider time zone differences in the creation timestamp of each drone.

## Dronetypes.java (Atheesen)

Dronetypes.java, handled by Atheesen, should contain relevant data for drone types.

## Documentation

At the end of the project, focus on documenting the code. Ensure clean code practices, including CamelCasing for all variables and functions. Create a comprehensive README with instructions and explanations for the entire project.


Drone.java:
- public Drone(){} - wäre gut Überlegungen zu machen was mit der Klasse geschehen soll. Polymorphism.
- the resulting product has to be a simply executable .jar file.
