MagicPaper

What can it do now?
(GUI)
Create custom GUIs in a simple way and achieve complex functionality by executing spells with the click of a button.
You can even use MagicPaper to create crafting tables, upgrade tables, and more.
Support dynamic button display, such as turning online player information into buttons displayed in your GUI – there will be as many buttons as there are players.
You can even execute commands like "tpa" when clicking on a player's button.

(Buff)
Grant players a buff that lasts for a specified duration. You can use MagicPaper's built-in buffs or decide what happens when a buff takes effect.

(Item Mastery)
Fully customize your items using MagicPaper, allowing you to tailor your item library.
MagicPaper comes with a set of embedding functions, letting you decide which items to embed into others and specify which attributes are added.
MagicPaper introduces a new concept for items: item description templates. With these templates, you can define how the items you want are described, supporting variables like PAPI and item NBT variables.

(Variable Mapping)
%magic_spell_name%
Get any value you want from a spell in MagicPaper.
You can decide which values should return what content. For example, obtaining a gold variable from PAPI, and if the balance is below 100, return "poor," or if it's below 1000, return "average"...
For example, you can assign different colors to players' chat formats based on their combat power, making higher-powered players have cooler chat formats. Your only limit is your imagination.
...

(Triggers)
What are triggers? Regardless of their meaning elsewhere, in MagicPaper, they are a straightforward concept.
Triggers are tasks you set to execute when something happens. For example, when you enter the server, send a welcome message.
MagicPaper offers many triggers that continue to grow. Make the most of them, and your server will be unique.

(Timers)
If we delve deeper, timers are also a special kind of trigger. They transition from executing on a specific event to running at regular intervals.
What can you do with them? For example, customize a buff called "Sparkling" and check each player in the timer. If a player has the "Sparkling" buff, you can choose to give them a glowing buff or create some attractive particles for a prestigious feel. This is just a simple example; as always, your only limit is your imagination.

(Skills)
MagicPaper was not designed for creating skills, but it unexpectedly provides a simple and efficient way to do so.
With ongoing updates to semantics, the range and richness of what you can do with skills will increase.
You might be accustomed to using other plugins to create skills, and that's fine. MagicPaper can actively support any skill plugins. They handle skill writing, and MagicPaper determines when and how the effects are triggered.
Currently, MagicPaper supports releasing skills from MythicMobs. You can use MagicPaper's triggers to decide who should trigger what skills at what times and locations.

(Unpredictable Extensibility and Inclusiveness)
Under the Magic language framework, adding any feature is straightforward and lightweight.
Having more features doesn't make it bulky; all extension features are called through callbacks by spells.
In some versions, certain features might not be available, but this doesn't affect overall usability. Any plugin feature supported by MagicPaper can be called and reused in the same environment for spell writing.

(Depth and Breadth of Development)
MagicPaper can serve as a programming language in its own right, implementing complex logic with its own syntax.
Most of the time, the purpose of Magic language is to have third parties implement complex logic in code as semantics, allowing users to call it simply with Magic syntax.
Magic is essentially an operational language, aimed at normalizing different operational methods in different environments, reducing the learning curve for users.

(Surprisingly Simple Syntax)
You might be hesitant when you see the term "programming," but honestly, the only barrier to learning Magic language is the sense of "mystique."
Install the plugin, imitate the examples, check the documentation, and experience the essence of MagicPaper.
What to do if you have questions? Ask!
What if you have requirements? Suggest!
It's that simple and convenient. What more could you ask for?

What can it do in the future?
When designing the framework, provisions were made for natural language processing.
At an appropriate stage, I will create deep learning models for translating Magic language into various national languages. This means that in your own Magic environment, you can input Chinese and it will automatically be converted into Magic spells.
Of course, the quality of the translation depends on the amount of data available. If not many people are using it, then it's a different story.

Github
Magic(Paper)
https://github.com/Yeqi99/MagicPaper
Magic(Self)
https://github.com/Yeqi99/Magic

Commands
/magicpaper reload - Reload the configuration.
/magicpaper spells - List all spells.
/magicpaper words <words> - Execute specific incantations.
/magicpaper spell <spell> - Execute a specific spell.
/magicpaper publicwords <words> - Execute specific incantations in a public environment.
/magicpaper publicspell <spell> - Execute a specific spell in a public environment.
/magicpaper functions - List all semantics.
/magicpaper functioninfo <function> - Get information about a semantic.
/magicpaper triggers - List available trigger names.
/magicpaper reloadall - Reset everything, including the Magic interpreter.
/magicpaper onload - Execute the spells in the onload section once.
/magicpaper boreremove <address> <index> - Remove an item from a specific inset hole.
/magicpaper restart - Restart the entire configuration (including the interpreter and onload execution).
/magicpaper coding - Toggle coding mode (requires admin privileges or specific permissions).
/magicpaper gui <guiName> - Open a GUI with the specified name.

Coding Mode
You can execute Magic syntax by sending messages directly in the game.
Format

!m+Syntax
Execute in a temporary context.
!pm+Syntax
Execute in a global context.
!clear
Clear the Coding private context.
magicpaper coding switches coding mode. People in coding mode can input code directly into the chat. Normal messages are for coding, not execution, and require keywords to control. Keywords:

 


go: Execute the currently written code.



clear: Clear the currently written code.



save id display_name description (can use \n for line breaks): Save the current code to the magic configuration.



spell spell_name: Execute the spell from the configuration (! executes the .m source file imported).



look: View the currently written code.
Configuration File Structure
 


import
When the plugin starts, it loads all .m files with Magic language sources into all contexts. Variable format: paper.import.source_file_id
 


item-format
Template for parsing item lores.
 


lang
Language files.
 


magic
YAML-formatted Magic language files that can be directly executed by the Minecraft command system.
 


onload
.m source files stored here will be automatically executed when the server starts. The onload command can also be executed in instructions.
 


timer
Write timer files in the default.yml format and place them in the timer folder to create timers. The default configuration file executes the HelloWorld spell every second; please remove it as needed.
 


trigger
Default supported trigger settings; you can directly add spells to triggers.
 


config.yml
General plugin configuration.

Permissions
Commands
magicpaper.command

Coding
magicpaper.coding

API
General
The general API exists in the form of static methods of the MagicPaperAPI class.

Semantic Expansion
Inherit from the Magic native NormalFunction or inherit from PaperFunction to implement the abstract Li.

Triggers
Inherit from the APITrigger class.

Timers
Inherit from the APITimer class.

Modules
Triggers
We can register spells into a trigger. When the trigger is activated, the registered spells will be cast. Different triggers have different predefined contexts.

Timers
We can manually create timers that run at regular intervals. Add the spells you want the timer to cast to the corresponding timer name. The timer will cast the registered spells each time it runs.

Item Embedding
Use semantics to open slots in an item, set the supported embedding slots, and directly embed items into an item in your backpack. Numeric attributes are added, while other types overwrite. Use the 'boreremove' command to remove embeddings.

Item Parsing Templates
The item-format folder in the configuration file stores item lore templates. All items can be set to parse templates, and each refresh will display lore according to the template format. You can use PAPI variables and item variables in the templates.

Buff Manager
MagicPaper comes with a Buff Manager. You can use the built-in buffs and create custom buffs.

GUI Manager
MagicPaper comes with a GUI Manager. Use the configuration file or semantics to customize your own GUI

PlaceholderAPI
%magic_spell_name%

Introduction

<!-- originmc -->
<repository>
    <id>originmc-repo</id>
    <url>https://maven.originmc.cn/repository/maven-public/</url>
</repository>

<!-- MagicPaper -->
<dependency>
  <groupId>cn.originmc.plugins</groupId>
  <artifactId>MagicPaper</artifactId>
  <version>1.5.0</version>
</dependency>

MagicPaper Semantics
Timer Control
Implementation
foliaTimer()
Create a folia timer
paperTimer()
Create a paper timer
addToTimer()
Add a magic task to the timer
Parameter Format
foliaTimer(timer name delay start interval data context)

paperTimer(timer name delay start interval data context)

addToTimer(timer name magic)
Return Value
null
Skills
Implementation
jumpSkill()
Jump in the direction you are facing
jumpToLocationSkill()
Jump in the direction of specified coordinates
Parameter Format
jumpSkill(player distance strength height strength)

jumpToLocationSkill(player target coordinates speed height strength)
Return Value
jumpSkill()
null
jumpToLocationSkill()
null
Cooldown
Implementation
addCoolDown()
Create a cooldown
checkCoolDown()
Check the cooldown
gocd()
Cooldown operation for magic
getcd()
Get cooldown time
Parameter Format
addCoolDown(id long(duration) cooldown reduction fixed cooldown reduction)

checkCoolDown(id)

gocd(cooldown ID duration cooldown message[optional])

getcd(cooldown ID)
Return Value
addCoolDown()
null
checkCoolDown()
bool
gocd()
null
getcd()
str
Common Objects
Implementation
player()
Player object
location()
Coordinate object
item()
Item object
citem()
Item clone
entity()
Entity
bossbar()
Boss bar
magicLocation()
Optimized coordinate object creation

All parameters must be strings, no conversion needed
Parameter Format
player()
player()
Default returns the magic executor with no parameters
player(player name string)
Get a player object based on the player name
player(object player type)
Restore object to player
player(coordinates radius quantity)
Get a specified number of players within a radius based on coordinates
location()
location()
Returns the executor's coordinates with no parameters
location(player)
Get player coordinates
location(object)
Restore object to location
location(x y z yaw pitch world)
Generate coordinates based on specific parameters
item()
item(player slot identifier)
mh
Main hand
oh
Offhand
h
Helmet
c
Chestplate
l
Leggings
s
Boots
item(player slot number)
cloneItem()
Same as item()
entity(player)

bossbar(key title color style)
Color
Link to BarColor
Style
Link to BarStyle
magicLocation()
magicLocation(x y z world)

magicLocation(x y z world yaw pitch)
Return Value
player()
When searching for players by coordinates, the target found is a list of players

All others return a single player object
location()
Coordinate object
item()
The item itself
cloneItem()
Item clone
entity()
Entity
bossbar()
Boss bar
magicLocation()
Coordinate object
Behavior
Implementation
consoleCommand()
Execute a command in the console
playerCommand()
Execute a command as a player
playerAsyncTeleport()
Asynchronously teleport a player
playerTeleport()
Teleport a player
senderToAllPlayer()
Send a message to all online players
senderToPlayer()
Send a message to a specific player
updateInventory()
Refresh the inventory item information
actionMsg()
Send action bar message
playerCloseInv()
Close the player's inventory
potionAdd()
Add a potion effect
Parameter Format
consoleCommand(command string)

playerCommand(player command string)

playerAsyncTeleport(player coordinates)

playerTeleport(player coordinates)

senderToAllPlayer(content string)

senderToPlayer(player content string)

updateInventory(player)

actionMsg(player message)

playerCloseInv(player)

potionAdd(player potion type potion level duration)
PotionEffectType List
Return Value
null
Buff
Implementation
buffAdd()
Add remaining time for a specific buff to a target
buffTimeGet()
Get the remaining time of a specific buff for a target
Parameter Format
buffAdd(target ID buff ID duration additional attributes string[optional] additional magic[optional]...)

buffTimeGet(target ID buff ID)
Return Value
buffAdd()
null
buffTimeGet()
A string representing a number
Gui
Implementation
guiOpen()
Open a GUI
guiPreviousPage()
Open the previous page of a GUI
guiNextPage()
Open the next page of a GUI
guiDataClear()
Clear GUI data
guiUpdate()
Update GUI data
Parameter Format
guiOpen(player Gui ID)

guiPreviousPage(player Gui ID)

guiNextPage(player Gui ID)

guiDataClear(player Gui ID)

guiUpdate(player Gui ID)
Return Value
guiOpen()
null
guiPreviousPage()
null
guiNextPage()
null
guiDataClear()
null
guiUpdate()
null
Button Data Source
Implementation
onlinePlayerButtons()
Map online players to button data source
Parameter Format
onlinePlayerButtons(item name string list)
~ represents the player name variable, supports PAPI variables
Return Value
items
HOOK
LuckPerms
Implementation
playerLPMetaSet()
Set a player's Meta key-value
playerLPTempMetaSet()
Set a player's temporary Meta key-value
playerLPMetaGet()
Get the value of a key for a player
playerHasPermission()
Check if a player has a permission
Parameter Format
playerLPMetaSet(player key value)

playerLPTempMetaSet(player key value time (long))

playerLPMetaGet(player key)

playerHasPermission(player permission)
Return Value
playerLPMetaSet()
null
playerLPTempMetaSet()
null
playerLPMetaGet()
string
playerHasPermission()
bool
PlaceholderAPI
Implementation
placeholderAPI()
Resolve PAPI variables
papiStr()
Resolve the entire string
Parameter Format
placeholderAPI(variable)
No need to add %
papiStr(string)
Return Value
string
AbolethPlus
Implementation
aboAdd()
Use ABO to add data
aboGet()
Get ABO data
aboGetUser()
Use ABO to get a player's UUID
aboSet()
Set ABO data
aboEdit()
Edit data
aboSetTime()
Set variable duration
aboAddItem()
Use ABO to store items
aboGetItem()
Use ABO to get items
Parameter Format
aboAdd(uuid key value)

aboGet(uuid key)

aboGetUser(player name)

aboSet(uuid key value)

aboEdit(uuid key value action)
action
ABO native action, refer to the official documentation
aboSetTime(uuid key long (time))

aboAddItem(uuid key item)

aboGetItem(uuid key)
Return Value
aboAdd()
null
aboGet()
str
aboUserGet()
str
aboSet()
null
aboEdit()
null
aboSetTime()
null
aboAddItem()
null
aboGetItem()
item
MythicMobs
Implementation
castSkill()
Execute MM skills
spawnMob()
Spawn MM mobs
Parameter Format
castSkill(player skill name power factor)

spawnMob(mob name coordinates)
Return Value
castSkill()
null
spawnMob()
entity
ItemsAdder
Implementation
IAItem()
Get IA items
placeIABlock()
Place IA custom blocks
Parameter Format
IAItem(IA item ID)

placeIABlock(IA block ID coordinates)
Return Value
IAItem()
item
placeIABlock()
bool
Vault
Implementation
vaultGet()
Get balance
vaultGive()
Give balance
vaultHas()
Check if there is enough balance
vaultTake()
Deduct balance
Parameter Format
vaultGet(player)

vaultGive(player amount)

vaultHas(player amount)

vaultTake(player amount)
Return Value
vaultGet()
int
vaultGive()
null
vaultHas()
bool
vaultTake()
bool
PlayerPoints
Implementation
playerpointsGet()
Get balance
playerpointsGive()
Give balance
playerpointsHas()
Check if there is enough balance
playerpointsSet()
Set balance
playerpointsTake()
Deduct balance
Parameter Format
playerpointsGet(player)

playerpointsGive(player amount)

playerpointsHas(player amount)

playerpointsSet(player amount)

playerpointsTake(player amount)
Return Value
playerpointsGet()
double
playerpointsGive()
bool
playerpointsHas()
bool
playerpointsSet()
bool
playerpointsTake()
bool
Execution Control
Implementation
foliaSpellAsyncExecute()
Asynchronous execution under Folia
foliaSpellExecute()
Synchronous execution under Folia
paperSpellAsyncExecute()
Asynchronous execution under Paper
paperSpellExecute()
Synchronous execution under Paper
publicContextGet()
Get public data context
playerTraversal()
Traverse the list of players
Parameter Format
foliaSpellAsyncExecute(spell1 spell2...)

foliaSpellExecute(spell1 spell2...)

paperSpellAsyncExecute(spell1 spell2...)

paperSpellExecute(spell1 spell2...)

publicContextGet()

playerTraversal(players player i)
Return Value
null
Triggers
Implementation
addSpellToTrigger()
Add a spell task to a trigger
triggerClearSpell()
Clear tasks from a trigger
Parameter Format
addSpellToTrigger(trigger name spell)

triggerClearSpell(trigger name)
Return Value
null
Information
Implementation
playerName()
Get the player's name
paperConstants()
Extract configuration file constants
stringComparison()
String comparison
ignoreCaseStringComparison()
Case-insensitive string comparison
color()
Correct color codes
isTimeRange()
Determine if it's within a time range
inLocationRange()
Determine if coordinates are within a specified area
Parameter Format
playerName(player)

paperConstants(key)

stringComparison(string1 string2)

ignoreCaseStringComparison(string1 string2)

color(string)

isTimeRange(time range)
8:30

8:30-9:30
inLocationRange(target coordinates range coordinates1 range coordinates2)
Return Value
playerName()
str
paperConstants()
str
stringComparison()
bool
ignoreCaseStringComparison()
bool
color()
str
isTimeRange()
bool
inLocationRange()
bool
Item Operations
Implementation
itemGivePlayer()
Send an item to a player
itemSetPlayer()
Set an item in a player's specific slot
itemLoreAddLine()
Add a line to the lore of an item in a player's specific slot
itemLoreRemove()
Remove a specific NBT line from an item
itemToString()
Convert an item to a string
stringToItem()
Convert a string to an item
itemNBTAdd()
Add NBT to an item
itemNBTGet()
Get the NBT value of an item
itemNBTRemove()
Remove a specific NBT from an item
itemGetName()
Get the item's name
itemSetName()
Set the item's name
itemTypeGet()
Get the material name of the item
itemTypeSet()
Set the material name of the item
itemLoreHasLineNoColor()
Search for a specific line content in the lore, ignoring color
itemLoreHasLine()
Search for a specific line content in the lore
itemVarParse()
Parse item variables
itemLoreGet()
Get a specific lore line
newItem()
Create a new item
refreshVar()
Refresh lore display according to the template
itemModelGet()
Get the custom model value of the item
itemModelSet()
Set the custom model value of the item
itemEnchantmentGet()
Get the enchantment level of the item
itemEnchantmentSet()
Set the enchantment level of the item
itemDamageSet()
Set the item's durability loss value
itemDamageGet()
Get the item's durability loss value
itemAmountSet()
Set the item quantity
itemAmountGet()
Get the item quantity
itemAttributeSet()
Set the item's attributes
itemAttributeGet()
Get a specific attribute value of the item
itemFlag()
Change the display label
itemEnableRefresh()
Allow MagicPaper to automatically parse the lore of the item
itemFormatSet()
Set the parsing template for the current item
itemInfoSet()
Set the item description (NBT)
itemInfoAdd()
Add a line to the item description
itemBoreSet()
Set an embedding hole
itemSupportBoreAddressSet()
Allow a specific item to be embedded in a specific embedding hole
addItemToBore()
Attempt to embed an item into another item's embedding hole
removeItemFromBore()
Attempt to remove a specific embedded item from an item
itemAddSpell()
Add a Spell trigger to the item
mergeNBT()
Merge NBT
Parameter Format
itemGivePlayer(player item)

itemSetPlayer(player item slot)
Slot details can be found in the item method
itemLoreAddLine(item text)

itemToString(item)

stringToItem(string)

itemNBTAdd(item path key value)
Root directory is /

For example, if you want to store data in the TEST directory under the root then the path would be /TEST
itemNBTGet(item path key value type)
Type
STRING

ITEMSTACK

INT

LONG

FLOAT

DOUBLE

UUID

BOOLEAN
itemNBTRemove(item path key)

itemGetName(item)

itemSetName(item item name)

itemTypeGet(item)

itemTypeSet(item material name)

itemLoreRemove(item line number)

itemLoreHasLineNoColor(item line content)

itemLoreHasLine(item line content)

itemVarParse(item content)

itemLoreGet(item line number)

newItem(material)

refreshVar(item template ID)
Item display templates are in the plugin's configuration folder item-format
itemModelGet(item)

itemModelSet(item value)

itemEnchantmentGet(item enchantment name)
Enchantment name rules
Default is Minecraft vanilla enchantments

Use format belonging to the enchantment: enchantment name to specify enchantments provided by other modules

Vanilla enchantment list
Link to Enchantment List
itemEnchantmentSet(item enchantment name level)

itemDamageSet(item value)
Sets how much durability has been consumed from the item, not how much durability remains

Setting it to -1 makes the item indestructible
itemDamageGet(item)
Indestructible items return -1
itemAmountSet(item value)

itemAmountGet(item)

itemAttributeSet(item attribute ID attribute name value effect location calculation method)
Effective Locations
mh
Main Hand
oh
Offhand
h
Helmet
c
Chestplate
l
Leggings
s
Boots
Calculation Methods
+
Direct addition
+%
Addition as a percentage
+*
Addition using a coefficient
Attribute Names
Attribute List
itemAttributeGet(item attribute ID attribute name effective location calculation method)
itemFlag(item tag code)
Code Meanings
1 - Hide Enchantments

2 - Hide Custom Attributes

3 - Hide Enchantments and Custom Attributes

4 - Hide {Unbreakable} (Indestructible)

8 - Hide {CanDestroy} (Can be Destroyed)

16 - Hide {CanPlaceOn} (Can be Placed On)

32 - Hide Most Information (Potion information, book author, fireworks effects, etc.)

63 - Hide All Information except Name and Lore
itemEnableRefresh(item)

itemFormatSet(item template ID)

itemInfoSet(item description)
Separate multiple lines with \n
itemInfoAdd(item description)

itemBoreSet(item socket address max socket count socketed item attribute address socketing item attribute address)
Address format refers to NBT address format
itemSupportBoreAddressSet(item socket address)
After setting this, the item can be socketed into items with the corresponding socket address
addItemToBore(socketed item socketing item)
Item-Related Functions
Implementation
itemAddSpell(item triggerMethod SpellName long(cooldown))
Only for main-hand items

Trigger Methods
left

right

shiftLeft

shiftRight
removeItemFromBore(item slot index)
Index starts from 0
itemAddSpell(item spellName cooldown effectLocation triggerMethod)
Trigger Methods
left

right

shiftLeft

shiftRight
mergeNBT(item1 item2)
Merge the NBT of item2 into item1
Return Value
itemGivePlayer()
null
itemSetPlayer()
null
itemLoreAddLine()
item
itemToString()
string
stringToItem()
item
itemNBTAdd()
null
itemNBTGet()
object
itemNBTRemove()
null
itemGetName()
string
itemSetName()
null
itemTypeGet()
string
itemTypeSet()
null
itemLoreRemove()
null
itemLoreHasLineNoColor()
boolean
itemLoreHasLine()
boolean
itemVarParse()
string
newItem()
item
refreshVar()
item
itemModelGet()
int
itemModelSet()
null
itemEnchantmentGet()
int
itemEnchantmentSet()
null
itemDamageSet()
null
itemDamageGet()
int
itemAmountSet()
null
itemAmountGet()
int
itemAttributeSet()
null
itemAttributeGet()
double
itemFlag()
null
itemEnableRefresh()
null
itemFormatSet()
null
itemInfoSet
item
itemInfoAdd()
item
itemBoreSet()
item
itemSupportBoreAddressSet()
item
addItemToBore()
item
removeItemFromBore()
item

If successful, the context variable "removeItem" is obtained
itemAddSpell()
item
mergeNBT()
item
Yaml
Implementation
yamlManager()
Create a Yaml manager.
newYaml()
Create a new YAML file using the manager.
yamlGet()
Retrieve data from YAML.
yamlSet()
Store a value in YAML.
yamlSave()
Save a specific YAML file from the manager to the hard drive.
yamlSaveAll()
Save all YAML files in the manager.
hasyamlkey()
Check if a specific key exists.
hasyaml()
Check if a YAML file with a specific name exists.
Parameter Format
yamlManager(path, folderName)

newYaml(Yaml manager, fileId)

yamlGet(Yaml manager, fileId, key)

yamlSet(Yaml manager, fileId, key, value)

yamlSave(Yaml manager, fileId)

yamlSaveAll(Yaml manager)

hasyamlkey(Yaml manager, fileId, key)

hasyaml(Yaml manager, fileId)
Return Values
yamlManager()
Yaml manager
newYaml()
null
yamlGet()
object
yamlSet()
null
yamlSave()
null
yamlSaveAll()
null
hasyamlkey()
boolean
hasyaml()
boolean
Random
Implementation
randomDouble()
Generate a random decimal within a range.
weightRandom()
Select a string with specified weights randomly.
randomUUID()
Generate a random UUID.
Parameter Format
randomDouble(starting value, ending value, decimal places)

weightRandom(string1, string2, string3...)
String format
Content|Weight
ABC|10
randomUUID()
Return Values
randomDouble()
double
weightRandom
string
randomUUID()
string
Biology
Implementation
newEntity()
Create a new entity.
Parameter Format
newEntity(entity type, coordinates)
Return Values
newEntity()
entity
BOSS Health Bar
Implementation
showBossBar()
Show the boss health bar.
hideBossBar()
Hide the boss health bar.
bossbarVisible()
Set boss health bar visibility.
bossbarGet()
Get the boss health bar.
addFlag()
Add a boss health bar flag.
Parameter Format
showBossBar(Player bossbar)

hideBossBar(Player bossbar)

bossbarVisible(true/false)

bossbarGet(key)

addFlag(flag)
Available flags
List of Flags
Return Values
showBossBar()
null
hideBossBar()
null
bossbarVisible()
null
bossbarGet()
bossbar
addFlag()
null
