package com.example.pix.data.room.mappers

import com.example.pix.data.room.PictureDbo
import com.example.pix.domain.entity.Picture

fun Picture.toPictureDbo(): PictureDbo = PictureDbo(id = id, title = title, url = url)