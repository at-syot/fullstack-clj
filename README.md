# minimal fullstack starter

## Feature

- SSR
- Hot reload
- Tailwindcss
- Client & Server routing

#### WIP state: Todos

1. SSR <input type="checkbox" checked/>
2. HOT reload <input type="checkbox" checked/>
3. Tailwindcss <input type="checkbox" />
4. Routing <input type="checkbox" />
   1. Server <input type="checkbox" checked/>
   2. Client <input type="checkbox" />

## Quick setup

```shell
npx create-uix-app@latest my-app # bare-bones project
npx create-uix-app@latest my-app --re-frame # adds re-frame setup
npx create-uix-app@latest my-app --fly-io # creates full stack app with Fly.io
npx create-uix-app@latest MyApp --react-native # setup cljs project in existing React Native project
npx create-uix-app@latest MyApp --expo # create a new React Native project using Expo
```

## Development

```shell
npm i # install NPM deps
npm run dev # run dev build in watch mode with CLJS REPL
```

## Production

```shell
npm run release # build production bundle
```
