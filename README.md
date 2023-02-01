![](assets\sprites\logo.png)
A modular java mindustry mod that works on PC.

## Mod dependency support / Java 16 / modules

I think this first mod that edit other mods and self if it enabled
and this mod uses java 16+ language level and mod is modular

modules list: <br>
| name        | description            | version |
| ----------- | ---------------------- | ------- |
| main        | mod main module        | 1.4     |
| tools       | mod tools              | 1.6     |
| cmdparser   | mod shell              | 1.0     |
| annotations | annotations processing | 1.1     |

## Building for Desktop Testing

1. Install JDK **17**.
2. Run `gradlew jar` [1].
3. Your mod jar will be in the `build/libs` directory. **Only use this version for testing on desktop. It will not work with Android.**