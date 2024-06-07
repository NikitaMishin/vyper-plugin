### Vyper smart contracts programming language plugin 
### for IntelliJ Platform

Features:
-----------
* syntax assistance with specified language grammar that facilitates *navigation*, *completion* and *highlighting*;
* development tools integration that includes: *Vyper compiler*, *SmartCheck static analyzer*, *MythX* with *projection* onto editor;
* fast function testing with *Vyper-debug/Vyper-run*
* *Docker*  so you basically need only IDEA and Docker :) ;
* nevertheless pre-alpha.
-----------

**Demo**
------------

see demo on [youtube](https://www.youtube.com/watch?v=M6f6xgcP4Xo&feature=youtu.be).

**Build:**
------------
 to build the plugin you should have IntelliJ IDEA. Clone the project, set your Intellij IDEA version in `build.gradle` file (line `20`) and run gradle buildPlugin task. After that, locate the created `.zip` in `/bild/distributions` folder and add it as external plugin (_"Install Plguin from Disk... "_).

**Test** 
------------
to test the plugin without installation you might run runIde task in gradle, which will execute IDEA instance with the plugin installed.

**Generate files**
------------
to generate files for the plugin we use the [Grammar Kit](https://plugins.jetbrains.com/plugin/6606-grammar-kit) plugin. To generate the files, you should have the plugin installed.
- Run the `Generate Parser Code` task in the `Vyper.bnf` file. That generates `VyperParser.java` file. However, the generator is not fully Kotlin compatible. We need to add a `Companion` call to `parseLight` manually.
- Run the `Generate JFlex Lexer` task in the `Vyper.bnf` file. That generates `_VyperLexer.flex` file.
- Run the `Run JFlex Generator` task in the `_VyperLexer.flex` file. That generates `VyperLexer.java` file.
