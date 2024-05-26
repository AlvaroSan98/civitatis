import com.alvaro.civitatis.model.DomainCharacter
import com.alvaro.civitatis.model.db.CharacterEntity
import com.alvaro.civitatis.model.remote.data.Result


fun Result.toDomainCharacter(): DomainCharacter {
    val thumbnailUrl = thumbnail.path.replace("http", "https") + "." + thumbnail.extension
    return DomainCharacter(id.toString(), thumbnailUrl, name, description)
}

fun Result.toEntityCharacter(): CharacterEntity {
    val thumbnailUrl = thumbnail.path.replace("http", "https") + "." + thumbnail.extension

    var comics = ""
    if (this.comics.available > 0) {
        this.comics.items.forEach { comics += "${it.name}," }
        comics.substring(0, comics.length - 2)
    }

    var series = ""
    if (this.series.available > 0) {
        this.series.items.forEach { series += "${it.name}," }
        series.substring(0, series.length - 2)
    }

    var stories = ""
    if (this.stories.available > 0) {
        this.stories.items.forEach { stories += "${it.name}," }
        stories.substring(0, stories.length - 2)
    }

    var urls = ""
    if (this.urls.isNotEmpty()) {
        this.urls.forEach { urls += "${it.url.replace(",", "")}," }
        urls.substring(0, urls.length - 2)
    }

    return CharacterEntity(
        id.toString(),
        name,
        description,
        thumbnailUrl,
        comics,
        series,
        stories,
        urls
    )
}