package com.leandro1995.seito.intent.config.ambient

abstract class IntentConfigAmbient<E> {

    abstract fun initConfig(event: E)
}