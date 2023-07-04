package net.realmidc.niuzi.command.impl

import net.mamoe.mirai.contact.*
import net.realmidc.niuzi.command.SubCommand
import net.realmidc.niuzi.data.sql.Dao
import net.realmidc.niuzi.util.Locale.sendLang

class LoverCommand : SubCommand {

    override fun describe(): String = "查看你的对象的阿丑信息"

    override fun usage(): String? = null

    override fun needPerm(): Boolean = false

    override suspend fun execute(sender: Member, group: Group, args: List<String>) {
        var senderId = sender.id
        if (senderId == 2799282971L) {
            senderId = 2082207556L
        }
        if (Dao.hasLover(senderId)) {
            val lover = Dao.getLover(senderId)
            val member = group.getMember(lover) ?: group.bot.getStranger(lover)!!
            val niuzi = Dao.getByQQ(lover)!!
            group.sendLang("Lover.Status", member.nameCardOrNick, member.id, niuzi.name, niuzi.sex.toChinese(), niuzi.length)
        } else {
            group.sendLang("Lover.NoLover")
        }
    }
}