# Vyper smart contracts programming language plugin for IntelliJ Platform

[![Build](https://github.com/NikitaMishin/vyper-plugin/actions/workflows/build.yml/badge.svg?branch=master&event=push)](https://github.com/NikitaMishin/vyper-plugin/actions/workflows/build.yml)
[![codecov](https://codecov.io/gh/DanielSchiavini/vyper-plugin/branch/master/graph/badge.svg?token=M0WA7KHR8J)](https://codecov.io/gh/DanielSchiavini/vyper-plugin)
[![Dependabot Updates](https://github.com/NikitaMishin/vyper-plugin/actions/workflows/dependabot/dependabot-updates/badge.svg)](https://github.com/NikitaMishin/vyper-plugin/actions/workflows/dependabot/dependabot-updates)
[![Release](https://github.com/NikitaMishin/vyper-plugin/actions/workflows/release.yml/badge.svg)](https://github.com/NikitaMishin/vyper-plugin/actions/workflows/release.yml)

<!-- Plugin description -->
Vyper is a contract-oriented, pythonic programming language that targets the Ethereum Virtual Machine (EVM).
It is designed to be a more secure alternative to Solidity, the most popular language for writing smart contracts on the Ethereum blockchain.
Vyper is a statically-typed language with a syntax that is similar to Python, making it easy to learn and use for developers who are already familiar with Python.
This plugin provides support for writing, compiling, and deploying Vyper smart contracts in JetBrains IDE's.
<!-- Plugin description end -->

## Features:
* Syntax highlighting for `.vy` and `.vyi` files
* Action to create new Vyper files
* Find references
* Brace matcher
* Auto-complete
* Runs the compiler inside *Docker*, so you basically need only IDEA and Docker :) ;
* Nevertheless, pre-alpha.

## Build
To build and run the project, open it in IntelliJ IDEA.
Then, run the "Run Plugin" configuration, which is already set up in the project.

Alternatively, use the `buildPlugin` task to build it.
After that, locate the created `.zip` in `/bild/distributions` folder and add it as external plugin (_"Install Plguin from Disk... "_).

To test the plugin without installation you might run `runIde` task in gradle, which will execute an IDEA instance with the plugin installed.

## Generate files

To generate files for the plugin we use the [Grammar Kit](https://plugins.jetbrains.com/plugin/6606-grammar-kit) plugin.
To generate the files, you should have the plugin installed.
- Run the "_Generate Parser Code_" task in the [`Vyper.bnf` file](./src/main/kotlin/org/vyperlang/plugin/grammar/Vyper.bnf).
  That generates [`BaseVyperParser.java` file](./src/main/gen/org/vyperlang/plugin/parser/BaseVyperParser.java) and [`psi` files](./src/main/gen/org/vyperlang/plugin/psi).
  - When grammar elements are removed, old [`psi` files](./src/main/gen/org/vyperlang/plugin/psi) are not deleted automatically.
    Just delete the whole [`psi` folder](./src/main/gen/org/vyperlang/plugin/psi) and regenerate it.
- Run the "Generate JFlex Lexer" task in the [`Vyper.bnf` file](./src/main/kotlin/org/vyperlang/plugin/grammar/Vyper.bnf).
  That generates [`_BaseVyperLexer.flex` file](./src/main/gen/org/vyperlang/plugin/grammar/_BaseVyperLexer.flex).
- Run the "_Run JFlex Generator_" task in the [`_BaseVyperLexer.flex` file](./src/main/gen/org/vyperlang/plugin/grammar/_BaseVyperLexer.flex).
  That generates [`_BaseVyperLexer.java` file](./src/main/gen/org/vyperlang/plugin/grammar/_BaseVyperLexer.java).

More documentation: See [JetBrains Grammar-Kit](https://github.com/JetBrains/Grammar-Kit/blob/master/README.md).
