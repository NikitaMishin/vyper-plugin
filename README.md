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
* Syntax assistance with specified language grammar that facilitates *navigation*, *completion* and *highlighting*;
* Development tools integration that includes: *Vyper compiler* with *projection* onto editor;
* Fast function testing with *Vyper-debug/Vyper-run*
* *Docker*  so you basically need only IDEA and Docker :) ;
* Nevertheless, pre-alpha.

## Demo
See demo on [YouTube](https://www.youtube.com/watch?v=M6f6xgcP4Xo).
**Note**: This video is extremely outdated.

## Build
To build and run the project, open it in IntelliJ IDEA.
Then, run the "Run Plugin" configuration, which is already set up in the project.

Alternatively, use the `buildPlugin` task to build it.
After that, locate the created `.zip` in `/bild/distributions` folder and add it as external plugin (_"Install Plguin from Disk... "_).

To test the plugin without installation you might run `runIde` task in gradle, which will execute an IDEA instance with the plugin installed.

## Generate files

To generate files for the plugin we use the [Grammar Kit](https://plugins.jetbrains.com/plugin/6606-grammar-kit) plugin.
To generate the files, you should have the plugin installed.
- Run the `Generate Parser Code` task in the `Vyper.bnf` file.
  That generates `VyperParser.java` and `psi` files.
  - However, the generator is not fully Kotlin compatible.
    We need to add a `Companion` call to [`parseLight`](./src/main/java/com/vyperplugin/parser/VyperParser.java) (line 24) manually.
  - If some grammar elements are removed, old `psi` files may be left in the project.
    They should be removed manually.
- Run the `Generate JFlex Lexer` task in the `Vyper.bnf` file.
  That generates `_VyperLexer.flex` file.
- Run the `Run JFlex Generator` task in the `_VyperLexer.flex` file.
  That generates `VyperLexer.java` file.

More documentation: [Grammar-Kit](https://github.com/JetBrains/Grammar-Kit/blob/master/README.md).
