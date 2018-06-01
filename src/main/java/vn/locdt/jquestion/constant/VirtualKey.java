package vn.locdt.jquestion.constant;

import jline.console.KeyMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum VirtualKey {
	ENTER((char)13),
	CTRL_C((char)3),
	CTRL_D((char)4),
	CTRL_Z((char)26),

	BACK_SPACE('\b'),
	TAB('\t'),

	// Arrow Keys
	UP(Arrays.asList("\033[0A", "\340\110", "\000\110", "\033[A", "\0330A", "\0340H")),
	DOWN(Arrays.asList("\033[0B", "\340\113", "\000\113", "\033[B", "\0330B", "\0340P")),
	RIGHT(Arrays.asList("\033[0C", "\340\115", "\000\115", "\033[C", "\0330C", "\0340M")),
	LEFT(Arrays.asList("\033[0D", "\340\120", "\000\120", "\033[D", "\0330D", "\0340K")),

	A('a'),B('b'),C('c'),D('d'),E('e'),F('f'),G('g'),H('h'),I('i'),J('j'),K('k'),L('l'),M('m'),
	N('n'),O('o'),P('p'),Q('q'),R('r'),S('s'),T('t'),U('u'),V('v'),W('w'),X('x'),Y('y'),Z('z'),

	NUM_0('0'),NUM_1('1'),NUM_2('2'),NUM_3('3'),NUM_4('4'),
	NUM_5('5'),NUM_6('6'),NUM_7('7'),NUM_8('8'),NUM_9('9');

	public static Map<List<CharSequence>, VirtualKey> keyCodeMap = new HashMap<>();
	public final static KeyMap keyMap = new KeyMap("jquestion-keymap");
	static {
		Arrays.stream(VirtualKey.values())
				.forEach(virtualKey -> keyCodeMap.put(virtualKey.getValues(), virtualKey));
		Arrays.stream(VirtualKey.values())
				.forEach(vk -> vk.getValues().forEach(v -> keyMap.bind(v, vk)));
	}

	private List<CharSequence> values;
	VirtualKey(char value) {this.values = Arrays.asList(String.valueOf(value));}
	VirtualKey(CharSequence value) {this.values = Arrays.asList(String.valueOf(value));}
	VirtualKey(List<CharSequence> values) {this.values = values;}
	public List<CharSequence> getValues() {
		return values;
	}

	public static VirtualKey keyOf(CharSequence value) {
		Map.Entry<List<CharSequence>, VirtualKey> foundEntry = keyCodeMap.entrySet()
				.stream().filter(e -> e.getKey().contains(value))
				.findFirst().orElse(null);

		if (foundEntry != null) return foundEntry.getValue();
		return null;
	}
}
