# MagicPaper Scenario Scripts

These examples are small `.m` scripts that demonstrate how MagicPaper exposes Minecraft server capabilities as Magic semantics.

Copy a script into the plugin's `magic/`, `onload/`, or trigger-driven workflow as needed. Scripts that use `self` expect a player context, such as a player trigger, item spell, GUI button spell, coding mode, or `/magicpaper words ... [player]`.

## Scripts

- `01-onload-broadcast.m`: broadcast a startup message after MagicPaper loads.
- `02-player-join-welcome.m`: send a welcome message and action bar to the current player.
- `03-cooldown-jump.m`: run a jump skill with cooldown and fall-immunity buff.
- `04-open-example-gui.m`: open the bundled `example` GUI for the current player.
- `05-give-named-reward.m`: create, name, and give a reward item to the current player.
