# URL Shortener

## We’d like you to build a website that functions as a URL Shortener:
 1. A user should be able to load the index page of your site and be presented with an input field where they can enter a URL.
2. Upon entering the URL, a “shortened” version of that url is created and shown to the user as a URL to the site you’re building.
3. When visiting that “shortened” version of the URL, the user is redirected to the original URL.
4. Additionally, if a URL has already been shortened by the system, and it is entered a second time, the first shortened URL should be given back to the user.
For example, if I enter http://www.apple.com/iphone/ into the input field, and I’m running the app locally on port 9000, I’d expect to be given back a URL that looked something like http://localhost:9000/1. Then when I visit http://localhost:9000/1, I am redirected to http://www.apple.com/iphone/.

## Expectations
Straight HTML or JavaScript.
Submit the project as a Git archive. Do not include artifacts. We might look at the history.
A small README is appreciated. A few words about your approach? How to compile/launch your application?
You may have to make some assumptions. It is expected. Please explain them.
Good developers write unit tests.
Use the JVM stack you are most comfortable with.
No project is ever done/complete. Your job is to convince us that you understood the problem and that we want to discuss your approach with you.
Do you really want to use a hash function? Think twice and motivate your choice.
You may want to use external dependencies e.g. database. It’s totally fine! Just tell us more about your choice.

## How to start
- run docker compose - (docker-compose up) it`s required for database or you can set another db via application.yaml in resources
- just start application ./gradlew bootRun