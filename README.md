# MagicPaper

MagicPaper 是 Magic 脚本语言在 Minecraft Paper / Folia 服务器中的实现。它把 Paper 服务器核心能力、常用插件能力和服务器业务逻辑封装成 Magic 语义，让服主或开发者可以用更短的文本编排 GUI、触发器、计时器、物品、Buff、经济、权限、技能等功能。

MagicPaper 的定位不是单纯的“技能插件”或“GUI 插件”，而是一个基于 Magic 的服务器功能编排层。复杂能力仍然由 Java 和第三方插件提供，MagicPaper 负责把这些能力注册成语义，使同样的文本量能够表达更多逻辑，并让第三方可以快速接入这一套语言。

## 当前状态

当前源码版本为 `1.5.15-SNAPSHOT`，插件运行版本号为 `1.5.15`。

当前实现包含：

- 基于 Magic 的脚本执行器，可同时启用 Magic 系统语义和 MagicPaper 领域语义。
- Paper / Folia 任务执行语义，支持同步与异步执行。
- 配置型魔咒、`.m` 源文件导入、服务器启动执行、事件触发执行、定时执行。
- GUI 系统，支持分页、按钮脚本、动态数据按钮。
- 物品系统，支持 NBT、Lore 模板、变量刷新、属性、镶嵌孔、物品触发魔咒。
- Buff、冷却、BossBar、粒子、实体、坐标、药水、Yaml、随机等基础模块。
- PlaceholderAPI、LuckPerms、Vault、PlayerPoints、MythicMobs、ItemsAdder、ProtocolLib、AbolethPlus、MMOItems、EpicCraftingsPlus、AuthMe、Adyeshach、RemoteKeyboardBukkit、BungeeCord 等可选接入。
- 面向第三方插件的 API：注册语义、注册触发器、注册计时器、执行魔咒、访问上下文、GUI、Buff、冷却和魔法物品。

`plugin.yml` 当前声明 `api-version: 1.13`，源码依赖 Paper/Folia `1.20.1-R0.1-SNAPSHOT` API。插件软依赖均为可选挂钩，不安装对应插件时，只影响对应语义能力。

## 与 Magic 的关系

Magic 是语言核心；MagicPaper 是 Minecraft 环境实现。

MagicPaper 启动时会创建 `MagicManager`，并根据 `config.yml` 中的开关注册语义：

```yaml
extended-syntax:
  system: true
  paper: true
```

- `system: true` 注册 Magic 内置语义，如变量、计算、控制流、容器、输入输出。
- `paper: true` 注册 MagicPaper 语义，如玩家、物品、GUI、触发器、经济、权限、技能等。

这意味着第三方插件也可以把自己的能力注册成 Magic 语义，让 MagicPaper 成为不同插件之间的低成本编排层。

## 构建与安装

构建需要 Java 和 Maven：

```bash
mvn package
```

构建产物位于 `target/`。将生成的 Jar 放入服务器 `plugins/` 目录后启动服务器即可。

首次启动时，如果 `config.yml` 中 `default-file: true`，插件会释放默认配置和示例文件。

当前源码 Maven 坐标：

```xml
<dependency>
  <groupId>cn.originmc.plugins</groupId>
  <artifactId>MagicPaper</artifactId>
  <version>1.5.15-SNAPSHOT</version>
</dependency>
```

当前源码依赖的 Magic 坐标为：

```xml
<dependency>
  <groupId>cn.origincraft.magic</groupId>
  <artifactId>Magic</artifactId>
  <version>1.2.4</version>
</dependency>
```

项目 `pom.xml` 中已经包含 Paper、Folia、PlaceholderAPI、NBTAPI、ProtocolLib、MythicMobs、ItemsAdder、Vault、PlayerPoints 等仓库与依赖配置。

## 配置目录

插件数据目录中的主要文件夹：

```text
MagicPaper/
  config.yml       插件总配置
  lang/            语言文件
  magic/           YAML 格式魔咒，可由命令、触发器、计时器、GUI 调用
  import/          启动时导入到上下文的 .m 源文件
  onload/          服务器启动后执行的 .m 源文件
  trigger/         事件触发器配置
  timer/           定时器配置
  gui/             GUI 配置
  item-format/     物品 Lore 解析模板
  attribute/       属性配置
  data/            公共数据
```

一个最小魔咒文件示例：

```yaml
spell:
  - stap(HelloWorld)
display: "HelloWorld"
description:
  - "Send a message to all online players"
```

一个触发器配置示例：

```yaml
execute-spell:
  - HelloWorld
```

一个计时器配置示例：

```yaml
type: paper
later: 20
interval: 20
execute-spell:
  - HelloWorld
```

## 命令

主命令为 `/magicpaper`，别名为 `/mp`，基础权限为 `magicpaper.command`。

```text
/magicpaper reload
/magicpaper reloadall
/magicpaper spells
/magicpaper words <words> [player]
/magicpaper spell <spell> [player]
/magicpaper publicwords <words>
/magicpaper publicspell <spell>
/magicpaper functions
/magicpaper functioninfo <function>
/magicpaper triggers
/magicpaper onload
/magicpaper boreremove <address> <index>
/magicpaper restart
/magicpaper coding
/magicpaper gui <id> [player]
/magicpaper clearguidata <id> [player]
/magicpaper updateguidata <id> [player]
/magicpaper lookailas <function>
```

说明：

- `words` 和 `publicwords` 用于直接执行一行 Magic 语义；命令参数中的逗号会被替换为空格。
- `spell` 和 `publicspell` 用于执行 `magic/` 目录中载入的魔咒。
- `public*` 使用全局上下文；普通执行会创建临时上下文并导入 `import/` 内容。
- `functioninfo` 会从当前已注册的 `ArgsFunction` 读取参数说明。
- `lookailas` 是当前代码中的命令拼写，用于查看语义别名。

## 编码模式

`/magicpaper coding` 可以切换玩家聊天编码模式。玩家需要 OP 或 `magicpaper.coding` 权限。

快捷执行：

```text
!m<Magic语句>   在临时上下文执行
!pm<Magic语句>  在全局上下文执行
!clear          清空 Coding 临时上下文
```

进入编码模式后，普通聊天会被写入当前代码缓冲区。可用关键字：

```text
go                         执行当前缓冲区
clear                      清空当前缓冲区
look                       查看当前缓冲区
spell <spell1> <spell2>    执行已配置魔咒
save <id> <display> <desc> 保存当前缓冲区为 magic/<id>.yml
```

## 当前模块

### 触发器

触发器用于在事件发生时执行魔咒。当前内置触发器包括：

- 服务器：`ServerOnLoad`、`ServerOnEnable`、`ServerOnDisable`
- 玩家：`PlayerJoin`、`PlayerInteract`、`PlayerDrop`、`PlayerBreak`、`PlayerPlace`、`PlayerTeleport`、`AsyncPlayerChat`、`PlayerRespawn`
- 实体与物品：`EntityDamage`、`ItemDrop`
- 计时器：`Timer`
- 可选挂钩：`BungeeCordMessage`、`PlayerKeyboard`、`EpicCraftingsPreCraft`、`EpicCraftingsCraft`、`EpicCraftingsPlaceClick`

也可以通过 `MagicPaperAPI.registerTrigger(...)` 注册自定义触发器。

### 计时器

计时器是按固定 tick 间隔执行魔咒的特殊触发器。当前支持 Paper 与 Folia 两类计时器，并可通过语义动态创建：

- `paperTimer()` / `ptimer()`
- `foliaTimer()` / `ftimer()`
- `addToTimer()` / `att()`

### GUI

GUI 系统支持配置菜单、分页、按钮点击执行魔咒，以及动态数据按钮。典型语义包括：

- `guiOpen()` / `opengui()`
- `guiPreviousPage()` / `ppage()`
- `guiNextPage()` / `npage()`
- `guiDataClear()` / `cleargui()`
- `guiUpdate()` / `upgui()`
- `onlinePlayerButtons()` / `pbuttons()`

### 物品

物品系统是 MagicPaper 的核心模块之一，当前支持：

- 获取、给予、设置玩家物品。
- 修改名称、类型、数量、CustomModelData、耐久、附魔、隐藏标签。
- 读取和写入 NBT。
- Lore 行增删查、变量解析、模板刷新。
- 自定义属性写入、属性读取、属性缓存。
- 物品镶嵌孔、支持镶嵌地址、添加/移除镶嵌物。
- 主手物品触发魔咒。
- 物品序列化与反序列化。

常见语义包括 `item()`、`itemGivePlayer()`、`itemSetPlayer()`、`itemNBTAdd()`、`itemNBTGet()`、`itemSetName()`、`newItem()`、`refreshVar()`、`itemAttributeSet()`、`itemBoreSet()`、`addItemToBore()`、`removeItemFromBore()`、`itemAddSpell()`、`mergeNBT()`。

### Buff 与冷却

Buff 模块用于给目标添加有持续时间的状态；冷却模块用于限制行为频率。

- `buffAdd()`
- `buffTimeGet()`
- `addCoolDown()` / `addcd()`
- `checkCoolDown()` / `checkcd()`
- `gocd()`
- `getcd()`

### Placeholder 与变量映射

MagicPaper 注册了 PlaceholderAPI 扩展，可通过以下形式取值：

```text
%magic_spell_name%
```

也可以在 Magic 中使用：

- `placeholderAPI()` / `papi()`
- `papiStr()`

用途包括按玩家状态返回不同文本、把战斗力映射为聊天颜色、把经济余额映射为称号等。

### 数据实体与 YAML

数据实体当前默认使用 YAML 存储，配置中预留了数据库连接字段。YAML 语义包括：

- `yamlManager()` / `yamlm()`
- `newYaml()` / `nyaml()`
- `yamlGet()` / `yamlg()`
- `yamlSet()` / `yamls()`
- `yamlSave()`
- `yamlSaveAll()`
- `hasyaml()`
- `hasyamlkey()`

### 常用对象与行为

对象语义：

- `player()` / `p()`
- `location()` / `loc()`
- `item()`
- `entity()`
- `bossbar()`
- `component()` / `minimsg()`
- `potion()`
- `particle()`

行为语义：

- `consoleCommand()` / `ccommand()`
- `playerCommand()` / `pcommand()`
- `senderToPlayer()` / `stp()`
- `senderToAllPlayer()` / `stap()`
- `playerTeleport()` / `ptp()`
- `playerAsyncTeleport()` / `patp()`
- `updateInventory()` / `upinv()`
- `actionMsg()`
- `playerCloseInv()` / `closeinv()`
- `potionAdd()`

### 第三方插件挂钩

当前代码中已经包含以下挂钩能力：

- LuckPerms：Meta 设置、临时 Meta、Meta 读取、权限判断。
- PlaceholderAPI：变量解析、整句解析、Magic 占位符扩展。
- Vault：余额读取、增加、扣除、判断是否足够。
- PlayerPoints：点券读取、增加、设置、扣除、判断是否足够。
- MythicMobs：释放技能、生成生物，并包含 MythicMobs 监听扩展。
- ItemsAdder：获取自定义物品、放置自定义方块。
- AbolethPlus：数据读取、写入、编辑、过期时间、物品存取。
- AuthMe：登录状态判断。
- EpicCraftingsPlus：合成相关触发器。
- RemoteKeyboardBukkit：键盘触发器。
- ProtocolLib：物品变量刷新等发包层支持。
- MMOItems、Adyeshach、BungeeCord：相关 Hook 与辅助能力。

## 第三方 API

主要入口是 `MagicPaperAPI`。当前 API 支持：

- `registerFunction(PaperFunction function, String... aliases)`：注册 MagicPaper 语义。
- `registerTrigger(MagicPaperTrigger trigger)`：注册触发器。
- `registerTimer(String id, MagicTimer timer)`：注册计时器。
- `getSpell(...)` / `getSpells()` / `isSpell(...)`：读取已载入魔咒。
- `execute(...)`：执行魔咒或单行语义。
- `createNormalContext()` / `createSpellContext(...)` / `getMainContext()`：创建和访问上下文。
- `getCoolDownManager()`、`addCoolDown(...)`、`getCoolDown(...)`：冷却操作。
- `getMagicItem(...)`：包装 Bukkit `ItemStack`。
- `hasMagicBuff(...)`、`getMagicBuffs(...)`、`getMagicBuffTime(...)`、`getMagicBuff(...)`：Buff 查询。
- `openGui(...)`、`addGuiSetting(...)`：GUI 操作。

新增语义时，优先继承 `PaperFunction`；如果不依赖 Bukkit/Paper，也可以继承 Magic 原生的 `ArgsFunction` 或 `OnlyStringFunction`。

## 一个最小示例

`magic/HelloWorld.yml`：

```yaml
spell:
  - stap(HelloWorld)
display: "HelloWorld"
description:
  - "Send a message to all players"
```

命令执行：

```text
/magicpaper spell HelloWorld
```

直接执行一行语义：

```text
/magicpaper words stap(HelloWorld)
```

在聊天中快捷执行：

```text
!mstap(HelloWorld)
```

## 设计理念

MagicPaper 的核心思想是让服务器功能变成可组合语义。

传统插件往往把“什么时候触发、对谁触发、触发什么、如何变化”写死在配置或代码里。MagicPaper 希望把这些拆开：

- 事件由触发器提供。
- 时间由计时器提供。
- 环境对象由 Paper 语义提供。
- 第三方能力由 Hook 语义提供。
- 具体逻辑由 Magic 文本编排。

这样，复杂 Java 代码可以沉淀为稳定语义，普通使用者只需要组合这些语义即可完成服务器玩法。

## 未来预期

- 继续完善语义文档生成，让 `/magicpaper functioninfo`、README 与源码保持一致。
- 稳定 Paper/Folia 执行模型，减少不同服务端调度差异带来的使用成本。
- 扩展数据实体存储，补全数据库实现与持久化策略。
- 强化 GUI、物品、触发器和 Buff 的组合能力，让它们成为可复用的服务器功能模块。
- 提供更清晰的第三方接入规范，使其他插件能快速把能力注册成 Magic 语义。
- 与 MagicRedis 等实际项目一起验证 Magic 在非 Minecraft 场景中的表达能力。
- 在样本足够时探索自然语言到 Magic 语义的转换，让中文或其他自然语言可以生成对应魔咒。

## 开源协议

本项目采用 GNU GPL-3.0 许可证。使用、修改和分发本项目代码时，请遵循 GPL-3.0 的相关要求。
