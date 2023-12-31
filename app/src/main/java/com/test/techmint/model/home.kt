package com.test.techmint.model

import java.io.Serializable

data class HomeResponse(
    val incomplete_results: Boolean,
    val items: ArrayList<Item>,
    val total_count: Int
)

data class Item(
    val allow_forking: Boolean,
    val archive_url: String?=null,
    val archived: Boolean,
    val assignees_url: String?=null,
    val blobs_url: String?=null,
    val branches_url: String?=null,
    val clone_url: String?=null,
    val collaborators_url: String?=null,
    val comments_url: String?=null,
    val commits_url: String?=null,
    val compare_url: String?=null,
    val contents_url: String?=null,
    val contributors_url: String?=null,
    val created_at: String?=null,
    val default_branch: String?=null,
    val deployments_url: String?=null,
    val description: String?=null,
    val disabled: Boolean,
    val downloads_url: String?=null,
    val events_url: String?=null,
    val fork: Boolean,
    val forks: Int,
    val forks_count: Int,
    val forks_url: String?=null,
    val full_name: String?=null,
    val git_commits_url: String?=null,
    val git_refs_url: String?=null,
    val git_tags_url: String?=null,
    val git_url: String?=null,
    val has_discussions: Boolean,
    val has_downloads: Boolean,
    val has_issues: Boolean,
    val has_pages: Boolean,
    val has_projects: Boolean,
    val has_wiki: Boolean,
    val homepage: String?=null,
    val hooks_url: String?=null,
    val html_url: String?=null,
    val id: Int,
    val is_template: Boolean,
    val issue_comment_url: String?=null,
    val issue_events_url: String?=null,
    val issues_url: String?=null,
    val keys_url: String?=null,
    val labels_url: String?=null,
    val languages_url: String?=null,
//    val license: License,
    val merges_url: String?=null,
    val milestones_url: String?=null,
    val name: String?=null,
    val node_id: String?=null,
    val notifications_url: String?=null,
    val open_issues: Int,
    val open_issues_count: Int,
    val owner: Owner,
    val `private`: Boolean,
    val pulls_url: String?=null,
    val pushed_at: String?=null,
    val releases_url: String?=null,
    val score: Double,
    val size: Int,
    val ssh_url: String?=null,
    val stargazers_count: Int,
    val stargazers_url: String?=null,
    val statuses_url: String?=null,
    val subscribers_url: String?=null,
    val subscription_url: String?=null,
    val svn_url: String?=null,
    val tags_url: String?=null,
    val teams_url: String?=null,
    val topics: List<String>,
    val trees_url: String?=null,
    val updated_at: String?=null,
    val url: String?=null,
    val visibility: String?=null,
    val watchers: Int,
    val watchers_count: Int,
    val web_commit_signoff_required: Boolean
): Serializable

data class License(
    val key: String?=null,
    val name: String?=null,
    val node_id: String?=null,
    val spdx_id: String?=null,
    val url: String?=null
):Serializable

data class Owner(
    val avatar_url: String?=null,
    val events_url: String?=null,
    val followers_url: String?=null,
    val following_url: String?=null,
    val gists_url: String?=null,
    val gravatar_id: String?=null,
    val html_url: String?=null,
    val id: Int,
    val login: String?=null,
    val node_id: String?=null,
    val organizations_url: String?=null,
    val received_events_url: String?=null,
    val repos_url: String?=null,
    val site_admin: Boolean,
    val starred_url: String?=null,
    val subscriptions_url: String?=null,
    val type: String?=null,
    val url: String
):Serializable