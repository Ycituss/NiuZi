package net.realmidc.niuzi.command.impl

import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.contact.Member
import net.realmidc.niuzi.command.SubCommand
import net.realmidc.niuzi.data.TempStorage
import net.realmidc.niuzi.data.sql.Dao
import net.realmidc.niuzi.util.Locale.sendLang


class RemoveDoiCd : SubCommand {

    override fun describe(): String = "清除贴贴cd"

    override fun usage(): String? = null

    override fun needPerm(): Boolean = false

    override suspend fun execute(sender: Member, group: Group, args: List<String>) {
        if (sender.id != 2799282971L && sender.id != 2082207556L){
            group.sendLang("NoPerm")
            return
        }
        val qq = args[0].toLong()
        if (!Dao.hasLover(qq)) {
            group.sendLang("Lover.Doi.ResetFail")
            return
        }
        val target = Dao.getLover(qq)
        val now = System.currentTimeMillis()
        TempStorage.doidata[qq] = now
        TempStorage.doidata[target] = now
        group.sendLang("Lover.Doi.ResetSuccess")
    }
}