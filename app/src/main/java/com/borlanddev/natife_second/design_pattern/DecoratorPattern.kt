package com.borlanddev.natife_second.design_pattern

// See MainRepository


class FacebookMessenger {
    fun sendMessage(message: String) {}
    fun sendImage(imageId: Int) {}
    fun makePost(post: String) {}
}

class SlackMessenger {
    fun sendMessage(message: String) {}
    fun sendImage(imageId: Int) {}
}

class TelegramMessenger {
    fun sendMessage(message: String) {}
    fun sendImage(imageId: Int) {}
}


class Messenger(
    private val facebookMessenger: FacebookMessenger,
    private val slackMessenger: SlackMessenger,
    private val telegramMessenger: TelegramMessenger
) {
    fun sendMessage(message: String) {
        facebookMessenger.sendMessage(message)
        slackMessenger.sendMessage(message)
        telegramMessenger.sendMessage(message)
    }

    fun sendImage(imageId: Int) {
        facebookMessenger.sendImage(imageId)
        slackMessenger.sendImage(imageId)
        telegramMessenger.sendImage(imageId)
    }

    fun makePost(post: String) {
        facebookMessenger.makePost(post)
    }
}