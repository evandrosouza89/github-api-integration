# GitHub API Integration

This project demonstrates a backend built with Spring Boot and a frontend built with Vue.js and Axios for backend
communication. It offers a single-page application that allows visitors to fetch the top 10 GitHub repositories given
two filters: language (C, PHP, Java, Python, and JavaScript) and a sorting method (stars, interactions, and reactions).

<p align="center">
    <img src="/screenshot/screenshot.png" height="65%" width="65%" alt="App screenshot">
</p>

[Check live demo on this link](http://152.67.46.243/#/)

## Project Structure

This project is divided into two minor projects: back-end and front-end, both nested projects are containerized by
Docker. There's a Docker Compose file at the top level of the project.

### Back-end (Spring Boot)

The backend is built using Spring Boot, a powerful Java-based framework for building web applications. It provides
RESTful APIs to communicate with the GitHub API and retrieve repository data based on user queries.

#### Features:

- RESTful APIs for fetching top GitHub repositories based on language and sorting method.
- Integration with GitHub API for fetching repository data.
- Docker containerization for easy deployment.

### Front-end (Vue.js + Axios)

The frontend is developed using Vue.js, a progressive JavaScript framework for building user interfaces. Axios is used
for making HTTP requests to the backend and fetching data from the GitHub API.

#### Features:

- Single-page application interface for user interaction.
- Form inputs for selecting language and sorting method.
- Display of top 10 GitHub repositories based on user filters.
- Responsive design for seamless user experience across devices.

## Installation and Usage

To run the project locally, follow these steps:

1. Clone the repository:

    ```bash
    git clone https://github.com/evandrosouza89/github-api-integration.git
    ```
2. Navigate to the project directory:

    ```bash
    cd github-api-integration
    ```
3. Start Docker containers using Docker Compose:

    ```bash
    docker-compose up -d
    ```
4. Access the application in your browser:

    ```
    http://localhost:80/#/
    ```



## Technologies Used
### Backend:

 - Java
 - Spring Boot
 - Docker

### Frontend:

- Vue.js
- Axios
- Docker

### License
This project is licensed under the MIT License.