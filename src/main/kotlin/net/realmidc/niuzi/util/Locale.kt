package net.realmidc.niuzi.util

import net.mamoe.mirai.contact.Group
import net.realmidc.niuzi.PluginMain
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.util.function.Function

object Locale {

    private val file: File by lazy {
        val file = File(PluginMain.configFolder, "locale.yml")
        if (!file.exists()) {
            file.createNewFile()
            val content = PluginMain.getResource("locale.yml")
            if (content != null) {
                val config = YamlConfiguration()
                config.loadFromString(content)
                config.save(file)
            }
        }
        file
    }

    private val config = YamlConfiguration.loadConfiguration(file)

    fun getLang(path: String, args: Function<String?, String?>? = null): String {
        if (config.contains(path)) {
            return if (config.isList(path)) {
                // FIXME: 因为 MessageFormat#format 出现魔幻问题，所以曲线救国想了另外一个方法
                val result = config.getStringList(path).joinToString("\n")
                if (args != null) {
                    args.apply(result)!!
                } else {
                    result
                }
            } else {
                if (args != null) {
                    args.apply(config.getString(path))!!
                } else {
                    config.getString(path)!!
                }
            }
        }
        return ""
    }

    suspend fun Group.sendLang(path: String, args: Function<String?, String?>? = null) {
        sendMessage(getLang(path, args))
    }

}