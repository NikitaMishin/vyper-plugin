<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# Vyper Plugin Changelog

## [Unreleased]

- Add support for vyi files ([#17](https://github.com/NikitaMishin/vyper-plugin/issues/17)), including specific errors to vyi vs vyper files
- Remove MythX and SmartCheck as they are deprecated ([#26](https://github.com/NikitaMishin/vyper-plugin/issues/26))
- Remove run action and update compile action ([#27](https://github.com/NikitaMishin/vyper-plugin/issues/27))
- Remove deprecated decorators from syntax (i.e. `@public`, `@private`).
- Add warning for deprecated @nonreentrant with entrancy key.
- New file icons

## [0.2.0-alpha.2] - 2024-06-26

### Changed

- Add support for PyCharm ([#22](https://github.com/NikitaMishin/vyper-plugin/issues/22))
- Update platform to version [`2024.1.4`](https://blog.jetbrains.com/idea/2024/06/intellij-idea-2024-1-4/)

### Added

- Created plugin based on [`com.vyperplugin`](https://plugins.jetbrains.com/plugin/19039-vyper)
- Updated dependencies ([#13](https://github.com/NikitaMishin/vyper-plugin/pull/13))
- Add support for Vyper 0.3.0 ([#19](https://github.com/NikitaMishin/vyper-plugin/pull/19))
- Renamed the plugin to `org.vyperlang.plugin` ([#21](https://github.com/NikitaMishin/vyper-plugin/pull/21))

## [0.2.0-alpha.1] - 2024-06-24

### Added

- Created plugin based on [`com.vyperplugin`](https://plugins.jetbrains.com/plugin/19039-vyper)
- Updated dependencies ([#13](https://github.com/NikitaMishin/vyper-plugin/pull/13))
- Add support for Vyper 0.3.0 ([#19](https://github.com/NikitaMishin/vyper-plugin/pull/19))
- Renamed the plugin to `org.vyperlang.plugin` ([#21](https://github.com/NikitaMishin/vyper-plugin/pull/21))

[Unreleased]: https://github.com/DanielSchiavini/vyper-plugin/compare/v0.2.0-alpha.2...HEAD
[0.2.0-alpha.2]: https://github.com/DanielSchiavini/vyper-plugin/compare/v0.2.0-alpha.1...v0.2.0-alpha.2
[0.2.0-alpha.1]: https://github.com/DanielSchiavini/vyper-plugin/commits/v0.2.0-alpha.1
