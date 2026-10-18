<!--
GENERATED — DO NOT EDIT
Source: daisyui/packages/docs/src/routes/(routes)/components/avatar/+page.md
Regenerate: just generate
-->

# Avatar

[DaisyUI documentation →](https://daisyui.com/components/avatar/)

User and entity representations. Renders `<div class="avatar ...">`.

```kotlin
// AvatarPresence: Online | Offline
fun FlowContent.daisyAvatar(
    id: HtmlId? = null,
    presence: ClassValues<AvatarPresence>? = null,
    placeholder: Boolean = false,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyAvatarClasses(presence: ClassValues<AvatarPresence>? = null, placeholder: Boolean = false)
```

```kotlin
fun FlowContent.daisyAvatarGroup(
    id: HtmlId? = null,
    extraClasses: String? = null,
    attrs: (DIV.() -> Unit)? = null,
    content: (DIV.() -> Unit),
)

// The same classes on an element you write yourself, or beside another component's:
fun Tag.daisyAvatarGroupClasses()
```
