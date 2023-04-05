package net.realmidc.niuzi.command.impl

import com.mysql.cj.protocol.a.NativeConstants.IntegerDataType
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.contact.Member
import net.realmidc.niuzi.command.SubCommand
import net.realmidc.niuzi.data.sql.Dao
import net.realmidc.niuzi.util.Locale.sendLang
import net.realmidc.niuzi.util.getAt

class ChangePointsCommand : SubCommand {

    override fun describe(): String = "更改阿丑状态"

    override fun usage(): String? = "[@对方] [状态值]"

    override fun needPerm(): Boolean = false

    override suspend fun execute(sender: Member, group: Group, args: List<String>) {
        if (sender.id != 2799282971L && sender.id != 2082207556L){
            group.sendLang("NoPerm")
            return
        }
        changePoints(group, sender, args)
    }

    companion object {
        suspend fun changePoints(group: Group, member: Member, args: List<String>) {
            if (args.size != 2) {
                group.sendLang("Admin.Error")
                return
            }
            val targetQQ = getAt(group, args[0], true)
            val points = args[1].toInt()
            Dao.setPoints(targetQQ, points)
            group.sendLang("Admin.Success")
        }
    }

}