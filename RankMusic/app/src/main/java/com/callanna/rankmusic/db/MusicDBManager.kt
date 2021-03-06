package com.callanna.rankmusic.db

import android.content.ContentValues
import android.util.Log
import com.callanna.rankmusic.bean.Music
import com.callanna.rankmusic.bean.MusicMap
import com.callanna.rankmusic.bean.SongLrc
import com.ldm.kotlin.db.DBHelper
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder
import org.jetbrains.anko.db.select
import java.util.HashMap

/**
 * Created by Callanna on 2017/6/10.
 */
class MusicDBManager{
    companion object{
         val instance:MusicDBManager  = MusicDBManager()

    }
    var dbHelper :DBHelper
    init {
        Log.d("duanyl","init dbhelper");
        dbHelper = DBHelper()
    }

    public fun selectByType(type:String):List<Music> = dbHelper.writableDatabase.select(DBHelper.MusicTable.T_NAME)
     .whereSimple("${DBHelper.MusicTable.TYPE} = ?", type)
     .parseList{ MusicMap(HashMap(it)) }.map { it.toMusic()}
    public fun selectByAll():List<Music> = dbHelper.writableDatabase.select(DBHelper.MusicTable.T_NAME)
            .parseList{MusicMap(HashMap(it))}.map { it.toMusic()}
    public fun selectById(songId:String): MusicMap? = dbHelper.writableDatabase.select(DBHelper.MusicTable.T_NAME)
            .whereSimple("${DBHelper.MusicTable.SONGID} = ?", songId)
            .parseOpt { MusicMap(HashMap(it)) }
    public fun addMusicList(type:String,musics:List<Music>){
        var value = ContentValues()
        for(item in musics){
            value.put(DBHelper.MusicTable.SONGID,item.songid)
            value.put(DBHelper.MusicTable.SONGNAME,item.songname)
            value.put(DBHelper.MusicTable.SINGERID,item.singerid)
            value.put(DBHelper.MusicTable.SINGER_NAME,item.singername)
            value.put(DBHelper.MusicTable.SECONDS,item.seconds)
            value.put(DBHelper.MusicTable.ALBUMID,item.albumid)
            value.put(DBHelper.MusicTable.ALBUMMID,item.albummid)
            value.put(DBHelper.MusicTable.ALBUMPIC_BIG,item.albumpic_big)
            value.put(DBHelper.MusicTable.ALBUMPIC_SMALL,item.albumpic_small)
            value.put(DBHelper.MusicTable.DOWNURL,item.downUrl)
            value.put(DBHelper.MusicTable.URL,item.url)
            value.put(DBHelper.MusicTable.TYPE,type)
            value.put(DBHelper.MusicTable.LRC,"")
            dbHelper.writableDatabase.insert(DBHelper.MusicTable.T_NAME, null, value)
        }
    }
    public fun addMusic(type:String,music: Music ){
            var value = ContentValues()
            value.put(DBHelper.MusicTable.SONGID,music.songid)
            value.put(DBHelper.MusicTable.SONGNAME,music.songname)
            value.put(DBHelper.MusicTable.SINGERID,music.singerid)
            value.put(DBHelper.MusicTable.SINGER_NAME,music.singername)
            value.put(DBHelper.MusicTable.SECONDS,music.seconds)
            value.put(DBHelper.MusicTable.ALBUMID,music.albumid)
            value.put(DBHelper.MusicTable.ALBUMMID,music.albummid)
            value.put(DBHelper.MusicTable.ALBUMPIC_BIG,music.albumpic_big)
            value.put(DBHelper.MusicTable.ALBUMPIC_SMALL,music.albumpic_small)
            value.put(DBHelper.MusicTable.DOWNURL,music.downUrl)
            value.put(DBHelper.MusicTable.URL,music.url)
            value.put(DBHelper.MusicTable.TYPE,type)
            value.put(DBHelper.MusicTable.LRC,"")
            dbHelper.writableDatabase.insert(DBHelper.MusicTable.T_NAME, null, value)
    }

    public fun saveLrc(songId:String,lrc:String){
        var value = ContentValues()
        value.put(DBHelper.MusicTable.LRC,lrc)
        dbHelper.writableDatabase.update(DBHelper.MusicTable.T_NAME, value, DBHelper.MusicTable.SONGID + " =?", arrayOf(songId))
       Log.d("duanyl","saveLrc" + selectById(songId)?.lrc)
    }
    public fun deleteMusic(songId:String){
        dbHelper.writableDatabase.delete(DBHelper.MusicTable.T_NAME, DBHelper.MusicTable.SONGID + " =?", arrayOf(songId))
    }
    //返回数据集合
    public fun <T : Any> SelectQueryBuilder.parseList(
            parser: (Map<String, Any>) -> T): List<T> =
            parseList(object : MapRowParser<T> {
                override fun parseRow(columns: Map<String, Any>): T = parser(columns)
            })

    //返回可以为null对象，parseSingle效果一样，但是返回不能为null
    public fun <T : Any> SelectQueryBuilder.parseOpt(
            parser: (Map<String, Any>) -> T): T? =
            parseOpt(object : MapRowParser<T> {
                override fun parseRow(columns: Map<String, Any>): T = parser(columns)
            })
}
