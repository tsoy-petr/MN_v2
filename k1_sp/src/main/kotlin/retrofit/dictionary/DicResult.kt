package retrofit.dictionary

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "DicResult")
class DicResult {
    @set:Element(name = "head", required = false)
    @get:Element(name = "head", required = false)
    var head: String? = null

    @set:ElementList(name = "def", inline = true, required = false)
    @get:ElementList(name = "def", inline = true, required = false)
    var defs: List<Def>? = null
}

@Root(name = "def")
class Def {
    @get:Attribute(name = "pos", required = false)
    @set:Attribute(name = "pos", required = false)
    var pos: String? = null

    @get:Attribute(name = "ts", required = false)
    @set:Attribute(name = "ts", required = false)
    var ts: String? = null

    @get:Element(name = "text", required = false)
    @set:Element(name = "text", required = false)
    var text: String? = null

    @get:ElementList(name = "tr", inline = true, required = false)
    @set:ElementList(name = "tr", inline = true, required = false)
    var trs: List<Tr>? = null
}


@Root(name = "ex")
class Ex {
    @get:Element(name = "text")
    @set:Element(name = "text")
    var text: String? = null

    @get:ElementList(name = "tr", inline = true)
    @set:ElementList(name = "tr", inline = true)
    var exTrs: List<ExTr>? = null
}


@Root(name = "tr")
class ExTr {
    @get:Element(name = "text")
    @set:Element(name = "text")
    var text: String? = null
}


@Root(name = "mean")
class Mean {
    @get:Element(name = "text")
    @set:Element(name = "text")
    var text: String? = null
}


@Root(name = "syn")
class Syn {
    @get:Attribute(name = "gen", required = false)
    @set:Attribute(name = "gen", required = false)
    var gen: String? = null
    @get:Attribute(name = "asp", required = false)
    @set:Attribute(name = "asp", required = false)
    var asp: String? = null
    @get:Attribute(name = "pos", required = false)
    @set:Attribute(name = "pos", required = false)
    var pos: String? = null
    @get:Element(name = "text", required = false)
    @set:Element(name = "text", required = false)
    var text: String? = null
}


@Root(name = "tr")
class Tr {
    @get:Attribute(name = "gen", required = false)
    @set:Attribute(name = "gen", required = false)
    var gen: String? = null
    @get:Attribute(name = "asp", required = false)
    @set:Attribute(name = "asp", required = false)
    var asp: String? = null
    @get:Attribute(name = "pos", required = false)
    @set:Attribute(name = "pos", required = false)
    var pos: String? = null
    @get:Element(name = "text", required = false)
    @set:Element(name = "text", required = false)
    var text: String? = null
    @get:ElementList(name = "syn", inline = true, required = false)
    @set:ElementList(name = "syn", inline = true, required = false)
    var syns: List<Syn>? = null
    @get:ElementList(name = "mean", inline = true, required = false)
    @set:ElementList(name = "mean", inline = true, required = false)
    var means: List<Mean>? = null
    @get:ElementList(name = "ex", inline = true, required = false)
    @set:ElementList(name = "ex", inline = true, required = false)
    var exes: List<Ex>? = null
}