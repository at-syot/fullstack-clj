FROM clojure:tools-deps AS builder

COPY . /usr/src/app
WORKDIR /usr/src/app

# Download and install nvm:
RUN apt update && \
  apt install curl -y && \
  curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.40.3/install.sh | bash && \
  export NVM_DIR="$HOME/.nvm" && \
  [ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh" && \
  [ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion" && \
  nvm install 22 && \
  nvm use 22 && \
  npm i && \
  npx @tailwindcss/cli -i ./resources/public/css/input.css -o ./resources/public/css/output.css --minify && \
  npx shadow-cljs release app

RUN clj -T:build uber

FROM openjdk:21
COPY --from=builder /usr/src/app/target/built-standalone.jar /app/app.jar

CMD java -jar /app/app.jar

