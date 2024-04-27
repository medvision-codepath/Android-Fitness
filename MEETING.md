
## Things we gotta work on:
- Database tables
- Parse medical student dictionary excel file
- Take the Bitfit stuff and put it into here
- Some kind of way to keep track for calories
- Extract base colorscheme from figma


### Meeting: 4/9/2024

Magfur: We can use a smartwatch 

Plan to intergrate smartwatch features into our app, to elevate user experience and engagement. By extending our app's functionality to smartwatches, users can conveniently acces key features and track health metrics seamlessly.  


#### Repo Link (org)
- https://github.com/medvision-codepath

- [ x ] TODO: Organize this such that it follows the template thing

#### Useful links

[Connecting to a smartwatch Android docs](https://developer.android.com/training/wearables/get-started/connect-phone?authuser=1)


[Learning components in figma](https://www.youtube.com/watch?v=KnmxD8LvHmA&t=50s&pp=ygUQZmlnbWEgY29tcG9uZW50cw%3D%3D)

[More components stuff in figma](https://www.youtube.com/watch?v=aGvWDDPsjUM&pp=ygUQZmlnbWEgY29tcG9uZW50cw%3D%3D)

[Fullblown app design flow tutorial](https://www.youtube.com/watch?v=PeGfX7W1mJk&t=1548s&pp=ygUZZmlnbWEgdHV0b3JpYWwgYXBwIGRlc2lnbg%3D%3D)

[Templates we can legally steal :v](https://www.figma.com/community/category/design-templates)


[We can actually import figma stuff!](https://developer.android.com/develop/ui/compose/tooling/relay/convert-designs-android-studio)

[How to Export Figma design to Android Studio!](https://www.youtube.com/watch?v=iA4SWccJNzI)

### AMOUNGUS
```
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣤⣤⣤⣤⣤⣤⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⡿⠛⠉⠙⠛⠛⠛⠛⠻⢿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⠋⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠈⢻⣿⣿⡄⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⣸⣿⡏⠀⠀⠀⣠⣶⣾⣿⣿⣿⠿⠿⠿⢿⣿⣿⣷⡄⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⣿⣿⠁⠀⠀⢰⣿⣿⡯⠁⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣷⡄⠀⠀
⠀⠀⣀⣤⣴⣶⣶⣿⡇⠀⠀⠀⢸⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣷⠀⠀
⠀⢰⣿⡟⠋⠉⣹⣿⡇⠀⠀⠀⠘⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⣶⣿⣿⣿⠀⠀
⠀⢸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠹⣿⣿⣿⣿⣶⣶⣶⣶⣶⣾⣿⣿⣿⡿⠀⠀⠀
⠀⣸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠉⠻⠿⣿⣿⣿⣿⡿⠿⠿⣿⣿⠋⠀⠀⠀⠀
⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣧⠀⠀⠀
⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠀⠀⠀
⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠀⠀⠀
⠀⢿⣿⡆⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀⠀
⠀⠸⣿⣧⡀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠃⠀⠀⠀
⠀⠀⠙⢿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⣰⣿⣿⣷⣶⣶⣶⣶⠶⠀⢸⣿⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⣽⣿⡏⠁⠀⠀⢸⣿⡇⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⢹⣿⡆⠀⠀⠀⣸⣿⠇⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⢿⣿⣦⣄⣀⣠⣴⣿⣿⠀⠀⠈⠻⣿⣿⣿⣿⡟⠁⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠈⠛⠻⠿⠿⠿⠿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀


A  M  O  N  G  U  S

```
