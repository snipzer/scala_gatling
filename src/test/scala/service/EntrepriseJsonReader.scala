package service

import java.io.Reader

import com.google.gson.stream.{JsonReader, JsonToken}

import scala.collection.mutable.ListBuffer
import entity.Entreprise


object EntrepriseJsonReader {

  def read(in: Reader): List[Entreprise] = readEntreprises(new JsonReader(in))

  def readEntreprises(reader: JsonReader): List[Entreprise] = {
    val entreprises = new ListBuffer[Entreprise]
    reader.beginArray()
    while (reader.hasNext) {
      entreprises += readEntreprise(reader)
    }
    reader.endArray()
    entreprises.toList
  }

  def readEntreprise(reader: JsonReader): Entreprise = {
    var id: String = ""
    var medecinId: String = ""
    var centreId: String = ""
    var secretaireId: String = ""

    reader.beginObject()
    while (reader.hasNext) {
      val name: String = reader.nextName()
      name match {
        case "id" => id = reader.nextString()
        case "medecinId" => medecinId = getProccessedString(reader, "20")
        case "centreId" => centreId = getProccessedString(reader, "1")
        case "secretaireId" => secretaireId = getProccessedString(reader, "3")
        case _ => reader.skipValue()
      }
    }
    reader.endObject()
    Entreprise(id, medecinId, centreId, secretaireId)
  }

  def getProccessedString(reader: JsonReader, defaultString: String): String = {
    if(reader.peek() == JsonToken.NULL) {
      reader.nextNull()
      defaultString
    } else {
      Option[String](reader.nextString()).getOrElse(Some(defaultString).get)
    }
  }

}