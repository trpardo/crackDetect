package com.pardo.crackdetect.core.nav

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import kotlin.text.StringBuilder

sealed class Destination(@StringRes val labelRes: Int = 0) {
    open class Activity(val bundle: Bundle?) : Destination()
    open class NavComponent(@StringRes label: Int) : Destination(label)
}

abstract class NavDestination(
    val dst: String,
    @StringRes labelRes: Int = 0,
    val params: List<TypeNavParam<out Any?>> = listOf()
) : Destination.NavComponent(labelRes) {
    val route: String get() = StringBuilder().apply {
        this.append(dst)
        params.forEach {
            this.append("${it.code}{${it.id}}")
        }
    }.toString()

    val arguments get() = mutableListOf<NamedNavArgument>().apply {
        params.forEach { navParam ->
            val namedNavArgument = navArgument(navParam.id) { type = navParam.type }
            this.add(namedNavArgument)
        }
    }

    fun createNavRoute(vararg args: Pair<String, *>) = StringBuilder().apply {
        val navigationArgs = mutableListOf<NavigationArg<*>>().apply {
            params.forEach { param ->
                args.firstOrNull { it.first == param.id }?.let { arg ->
                    this.add(NavigationArg(param, arg.second))
                } ?: kotlin.run {
                    this.add(NavigationArg(param, param.defaultValue))
                }
            }
        }

        this.append(dst)
        navigationArgs.forEach {
            this.append("${it.type.code}${it.value}")
        }
    }.toString()
}

data class NavigationArg<out T>(
    val type: TypeNavParam<out T>,
    val value: T?
)

sealed class TypeNavParam<T>(
    val id: String,
    val type: NavType<T>,
    val code: String,
    val defaultValue: T? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TypeNavParam<*>

        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}