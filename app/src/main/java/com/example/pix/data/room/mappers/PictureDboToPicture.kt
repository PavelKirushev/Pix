package com.example.pix.data.room.mappers

import com.example.pix.data.room.PictureDbo
import com.example.pix.domain.entity.Picture

fun PictureDbo.toPicture(): Picture = Picture(id = id, url = url, title = title)