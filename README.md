# Native Syntax Highlighter for Android

[![](https://jitpack.io/v/vic797/android_native_code_view.svg)](https://jitpack.io/#vic797/prowebview)

This library allows you to highlight syntax in an Android Native way using `Spannable`.
A different way than other syntax highlighters that use a `WebView` with a Javascript library.

Features:
* Optimized: set the spans only in the current line, in a region or in the entire view.
* Personalization: Add your own rules using JSON. They can be loaded from a JSON string or a JSON file in assets or in the external storage.
* Read Only: use it as a `TextView`
* Any number of rules: add all the rules that you want (could affect the performance)

By default this library does not include any syntax; you can create your own syntax files using JSON or by
adding the `Syntax` rules by code.

## Something extra!

This library includes the `LineCountLayout` witch extends from `ScrollView` and allows you to add line numbers to any `EditText`.

## Add to your project

Add this to your main gradle:

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

And add the reference to your app-level gradle:

```
dependencies {
	compile 'com.github.vic797:android_native_code_view:VERSION'
}
```

## Usage

See the wiki to know how to use it.

## Licence

```
Copyright 2017 Victor Campos

Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```