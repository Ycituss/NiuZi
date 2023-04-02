package net.realmidc.niuzi.command.impl

import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.contact.nameCardOrNick
import net.realmidc.niuzi.api.NiuziAPI.pointsState
import net.realmidc.niuzi.command.SubCommand
import net.realmidc.niuzi.data.sql.Dao
import net.realmidc.niuzi.util.Locale.sendLang
import net.realmidc.niuzi.util.getAt

class AdminStatusCommand : SubCommand {

    override fun describe(): String = "查看别人的牛子"

    override fun usage(): String? = null

    override fun needPerm(): Boolean = false

    override suspend fun execute(sender: Member, group: Group, args: List<String>) {
        get(group, sender, args)
    }

    companion object {
        suspend fun get(group: Group, member: Member, args: List<String>) {
            val targetQQ = getAt(group, args[0], true)
            val niuzi = Dao.getByQQ(targetQQ)
            if (niuzi == null) {
                group.sendLang("Status.NoNiuZi")
                return
            }
            if (member.pointsState() == -1) {
                group.sendLang("Status.Status-1", "", targetQQ, niuzi.name, niuzi.sex.toChinese(), niuzi.length)
            } else if (member.pointsState() == 3) {
                group.sendLang("Status.Status3", "", targetQQ, niuzi.name, niuzi.sex.toChinese(), niuzi.length)
            } else {
                group.sendLang("Status.Status", "", targetQQ, niuzi.name, niuzi.sex.toChinese(), niuzi.length)
            }
        }
    }
}