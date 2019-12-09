=============================================
Extração das KEYS

Text = { 20:40 Item: 2 ammo_rockets}
Regex: (?<=:\d\d\s)(.*?)(?=:)
Extract = {Item}
=============================================
Extração do tempo

Text = { 20:40 Item: 2 ammo_rockets}
Regex: .*(?<=\d:\d\d)
Extract: { 20:40}
=============================================
Extrair valores após KEY

Text = { 20:40 Item: 2 ammo_rockets}
Regex: (?<=[a-zA-Z]:\s).+$
Extract: { 2 ammo_rockets}

After number:
(?<=\d\s\b).*


=============================================

Extrair id após KEY
Text = { 25:11 Item: 2 item_armor_body}
Regex: (?<=[a-zA-Z]:\s)\d+
Extract: {2}

=============================================
Extrair nome da KEY: ClientUserinfoChanged

Text = { 16:36 ClientUserinfoChanged: 2 n\Oootsimo\t\0\model\razor/id\hmodel\razor/id\g_redteam\\g_blueteam\\c1\3\c2\5\hc\100\w\0\l\0\tt\0\tl\0 }
Regex: (?<=n\\)(.*?)(?=\\t)
Extract: {Oootsimo}

=============================================
Extração de Kills:

EXTRAIR OS IDS
	(?<=:\s).*\d
KILLER:
	(?<=\d:\s).*(?=\skilled)
KILLED:
	(?<=\skilled\s).*(?=\sby)
MODE:
	(?<=\sby\s).*
=============================================