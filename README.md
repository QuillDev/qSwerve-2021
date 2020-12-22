# qSwerve 2021
## Code base for swerve models written by QuillDev, intended for use in FRC

# Installing
```bash
cd yourDirectory
git init
git pull https://github.com/QuillDev/qSwerve-2021.git
cd qSwerve-2021.git
code .
```

# Configuring
In the default configuration drive motor ids are ALWAYS *10 more* than azimuth motor ids
For example if your front left azimuth motors id is *7* the drive motor id will be set to *17*

# TODO
* Add good support for SPARK MAX + NEO for drive motor
* Add support for SPARK MAX + NEO 550 for steering motor
* Abstract classes out a bit more!