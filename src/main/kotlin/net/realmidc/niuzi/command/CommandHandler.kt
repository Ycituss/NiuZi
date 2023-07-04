package net.realmidc.niuzi.command

import net.mamoe.mirai.console.command.CommandSender.Companion.asMemberCommandSender
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.event.subscribeGroupMessages
import net.realmidc.niuzi.PluginMain
import net.realmidc.niuzi.command.impl.*
import net.realmidc.niuzi.util.Locale
import net.realmidc.niuzi.util.permitted

object CommandHandler {

    private val commands = hashMapOf<String, SubCommand>()

    fun init() {
        registerCommands("变女性", ChangeToWomanCommand())
        registerCommands("领养阿丑", GetCommand())
        registerCommands("我的阿丑", StatusCommand())
        registerCommands("改阿丑名", NameCommand())
        registerCommands("比划比划", PKCommand())
        registerCommands("管理命令", AdminCommand())
        registerCommands("我的对象", LoverCommand())
        registerCommands("我的宝贝", LoverCommand())
        registerCommands("搞对象", LoveRequestCommand())
        registerCommands("贴贴！", DoiCommand())
        registerCommands("处理请求", RequestCommand())
        registerCommands("我要分手", LeaveCommand())
        registerCommands("群阿丑排行", TopCommand())
        registerCommands("阿丑榜", TopCommand())
        registerCommands("清除贴贴cd", RemoveDoiCd())
        registerCommands("清除比划cd", RemovePKCd())
        registerCommands("查看阿丑", AdminStatusCommand())
        registerCommands("更改状态", ChangePointsCommand())

        PluginMain.globalEventChannel().subscribeGroupMessages {
            startsWith("", removePrefix = true) {
                val command = it.split(" ")
                val root = command[0]
                val args = command.drop(1)
                if (root == "阿丑系统") {
                    val builder = StringBuilder()
                    builder.append(Locale.getLang("CommandHeader") + "\n")
                    commands.forEach { (name, executor) ->
                        if (executor.needPerm()) {
                            if (!sender.asMemberCommandSender().permitted()) {
                                return@forEach
                            }
                        }
                        builder.append(Locale.getLang("CommandHelper", name, executor.usage() ?: "", executor.describe() ?: "") + "\n")
                    }
                    group.sendMessage(builder.toString())
                }
                commands.forEach { (name, executor) -> if (root == name) executor.execute(sender, group, args) }
            }
        }
    }

    private fun registerCommands(name: String, executor: SubCommand) {
        if (commands.containsKey(name)) {
            PluginMain.logger.error("重复注册子命令：$name")
            return
        }
        commands[name] = executor
    }
}