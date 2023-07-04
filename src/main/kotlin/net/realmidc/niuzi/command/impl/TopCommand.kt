package net.realmidc.niuzi.command.impl

import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.contact.Member
import net.mamoe.mirai.contact.getMember
import net.mamoe.mirai.contact.nameCardOrNick
import net.mamoe.mirai.message.data.ForwardMessage
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.utils.ExternalResource.Companion.sendAsImageTo
import net.realmidc.niuzi.command.SubCommand
import net.realmidc.niuzi.data.sql.Dao
import net.realmidc.niuzi.util.SaveHtml2Image
import org.junit.rules.ExternalResource
import java.io.Externalizable

import java.io.File
import java.io.FileInputStream
import java.lang.reflect.Executable


/**
 * NiuZi
 * net.realmidc.niuzi.command.impl.TopCommand
 *
 * @author xiaomu
 * @since 2022/11/30 8:19 PM
 */
class TopCommand : SubCommand {

    override fun usage(): String? = null

    override fun needPerm(): Boolean = false

    override fun describe(): String = "查看阿丑排行榜"

    override suspend fun execute(sender: Member, group: Group, args: List<String>) {
        val time = System.currentTimeMillis()
        val nodeList = arrayListOf<ForwardMessage.Node>()
        Dao.getAll(group).forEachIndexed { index, niuZi ->
            nodeList.add(
                ForwardMessage.Node(
                    senderId = group.bot.id,
                    time = ((time / 1000) + 1).toInt(),
                    senderName = group.bot.nameCardOrNick,
                    message = PlainText(
                        "<td>${index + 1}</td><td>${niuZi.name}</td><td>${group.getMember(niuZi.owner)!!.nameCardOrNick}</td><td>${niuZi.length}</td>"
                    )
                )
            )
        }
        if (nodeList.isEmpty()) {
            nodeList.add(
                ForwardMessage.Node(
                    senderId = group.bot.id,
                    time = ((time / 1000) + 1).toInt(),
                    senderName = group.bot.nameCardOrNick,
                    message = PlainText("太可惜了，本群还没有人领养过阿丑")
                )
            )
        }
        var forwardMessage = ForwardMessage(
            brief = "[聊天记录]",
            preview = listOf("木子:我要和群主谈恋爱", "绕道走:畜生,我才13岁啊", "木子:我不管,你130岁我也要和你谈", "绕道走:6"),
            source = "聊天记录",
            summary = "查看7条转发消息",
            title = "群聊的聊天记录",
            nodeList = nodeList
        )
        when ((0..5).random()) {
            0 -> {
                forwardMessage = ForwardMessage(
                    brief = "[聊天记录]",
                    preview = listOf("大聪明:最近一睡觉就鼻塞头疼", "晴天小猪:鼻塞头是啥啊", "菲菲公主:6"),
                    source = "聊天记录",
                    summary = "查看14条转发消息",
                    title = "群聊的聊天记录",
                    nodeList = nodeList
                )
            }
            1 -> {
                forwardMessage = ForwardMessage(
                    brief = "[聊天记录]",
                    preview = listOf("木子:我要和群主谈恋爱", "绕道走:畜生,我才13岁啊", "木子:我不管,你130岁我也要和你谈", "绕道走:6"),
                    source = "聊天记录",
                    summary = "查看5条转发消息",
                    title = "群聊的聊天记录",
                    nodeList = nodeList
                )
            }
            2 -> {
                forwardMessage = ForwardMessage(
                    brief = "[聊天记录]",
                    preview = listOf("黑蛋:你喜欢奶狗还是狼狗", "桃子:狼吧", "黑蛋:那你觉得我是哪种", "桃子:是个土狗"),
                    source = "聊天记录",
                    summary = "查看23条转发消息",
                    title = "群聊的聊天记录",
                    nodeList = nodeList
                )
            }
            3 -> {
                forwardMessage = ForwardMessage(
                    brief = "[聊天记录]",
                    preview = listOf("幽暗:失败是成功之母，那成功之父是谁呢", "偷吃一口月亮:给我转账10元，你就是成功支付", "拼的多省的多:给我转5块就行,价格更低,更划算"),
                    source = "聊天记录",
                    summary = "查看11条转发消息",
                    title = "群聊的聊天记录",
                    nodeList = nodeList
                )
            }
            4 -> {
                forwardMessage = ForwardMessage(
                    brief = "[聊天记录]",
                    preview = listOf("星:\uD83D\uDD25 \uD83D\uDDE1 \uD83D\uDE20 枪尖已经点燃\uD83D\uDD25 \uD83D\uDD25 \uD83D\uDCA5 炎枪—— \uD83E\uDD3A\uD83D\uDD25 ➡️ ➡️ ➡️ \uD83D\uDCA5 冲锋—— \uD83E\uDD3A\uD83D\uDD25 \uD83D\uDD2A ↘️ \uD83C\uDF0B \uD83E\uDD3A", "三月七:让你尝尝\uD83D\uDC57 \uD83D\uDC47 ❄️ 本姑娘的厉害\uD83D\uDE2C\uD83D\uDC4C \uD83C\uDFF9 ❄️ 嘿——♐ ♐ ♐ \uD83C\uDF86 ❄️ \uD83D\uDC30 ❄️ \uD83D\uDC30❄️ \uD83D\uDC30❄️ \uD83D\uDC30 ❄️ \uD83D\uDC30"),
                    source = "聊天记录",
                    summary = "查看9条转发消息",
                    title = "群聊的聊天记录",
                    nodeList = nodeList
                )
            }
            5 -> {
                forwardMessage = ForwardMessage(
                    brief = "[聊天记录]",
                    preview = listOf("黑塔:这么大的钻石\uD83D\uDC8E，送给你！转圈圈～转圈圈～转圈圈～转圈圈～转圈圈(吨吨吨吨)\uD83D\uDD28\uD83D\uDD28\uD83D\uDD28", "丹恒:洞天幻化\uD83D\uDCAB长梦一觉\uD83C\uDF41\uD83C\uDF42破！\uD83D\uDDE1️⚔️"),
                    source = "聊天记录",
                    summary = "查看31条转发消息",
                    title = "群聊的聊天记录",
                    nodeList = nodeList
                )
            }
        }

        val inputStream = FileInputStream(getImg(nodeList))
        inputStream.sendAsImageTo(group)
//        group.sendMessage(forwardMessage)
    }

    fun getImg(arrayList: ArrayList<ForwardMessage.Node>): String {
        val htmlPath = "./data/Image/achou/achou.html"
        val fileHtml = File(htmlPath)
        var html = "<meta charset=\"utf-8\">\n"
        html += "<head><style type=\"text/css\"><!--@import url('style/style.css');--></style></head>"
        html += "<body><div class=\"div_wrapper\"><div class=\"div_mc_ver\"><h3 class=\"h_mc_ver\">阿丑榜</h3><h3 class=\"h_mc_ver\"></h3>\n"
        html += "<table style=\"margin: auto\">\n" +
                "\t\t\t\t<tr class=\"tr_header\">\n" +
                "\t\t\t\t\t<td>排名</td>\n" +
                "\t\t\t\t\t<td>阿丑名</td>\n" +
                "\t\t\t\t\t<td>主人</td>\n" +
                "\t\t\t\t\t<td>长度(厘米)</td>\n" +
                "\t\t\t\t</tr>"

        html += buildString {
            for (item in arrayList) {
                append("<tr class=\"tr_mod_entry_even\" style=\"margin: auto\">${item.messageChain}</tr>")
            }
        }
        html += "</table></div></div></body></html>"
        fileHtml.writeText(html)

        val saveHtml2Image = SaveHtml2Image()

        return saveHtml2Image.html2Image(htmlPath)
    }

}